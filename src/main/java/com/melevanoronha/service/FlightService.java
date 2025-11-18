package com.melevanoronha.service;

import com.melevanoronha.enumerator.CapitalBrasileira;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Serviço responsável por buscar preços de passagens aéreas usando a API Amadeus.
 * Utiliza OAuth2 para autenticação e retorna valores estimados como fallback.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {

    private static final String DESTINO_IATA = "FEN";
    private static final String TRAVELER_TYPE_ADULT = "ADULT";
    private static final String SOURCE_GDS = "GDS";
    private static final String CURRENCY_BRL = "BRL";
    private static final int MAX_RESULTS = 5;
    private static final int DAYS_AHEAD_DEPARTURE = 30;
    private static final long TOKEN_REFRESH_MARGIN_MS = 300_000L;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final double MULTIPLIER_RECIFE = 0.8;
    private static final double MULTIPLIER_NORDESTE = 0.9;
    private static final double MULTIPLIER_SUDESTE = 1.2;
    private static final double MULTIPLIER_SUL = 1.3;
    private static final double MULTIPLIER_CENTRO_OESTE_NORTE = 1.4;

    private final RestTemplate restTemplate;

    @Value("${flight.api.enabled:false}")
    private boolean apiEnabled;

    @Value("${flight.api.base-url:}")
    private String apiBaseUrl;

    @Value("${flight.api.key:}")
    private String apiKey;

    @Value("${flight.api.secret:}")
    private String apiSecret;

    @Value("${flight.api.auth-url:https://test.api.amadeus.com/v1/security/oauth2/token}")
    private String authUrl;

    @Value("${flight.fallback.price-per-person:1500.0}")
    private double fallbackPricePerPerson;

    private String accessToken;
    private long tokenExpirationTime;

    public BigDecimal buscarPrecoPassagens(String origem, Integer numeroPessoas, Integer duracaoDias) {
        try {
            if (isAmadeusConfigured()) {
                return buscarPrecoViaAmadeus(origem, numeroPessoas, duracaoDias);
            }
            log.info("API Amadeus não configurada, usando valor estimado");
            return calcularPrecoEstimado(origem, numeroPessoas);
        } catch (Exception e) {
            log.error("Erro ao buscar preço das passagens: {}", e.getMessage(), e);
            return calcularPrecoEstimado(origem, numeroPessoas);
        }
    }

    private boolean isAmadeusConfigured() {
        return apiEnabled 
            && apiBaseUrl != null && !apiBaseUrl.isEmpty()
            && apiKey != null && !apiKey.isEmpty()
            && apiSecret != null && !apiSecret.isEmpty();
    }

    private BigDecimal buscarPrecoViaAmadeus(String origem, Integer numeroPessoas, Integer duracaoDias) {
        return Optional.ofNullable(CapitalBrasileira.extrairCodigoIATA(origem))
            .map(codigoOrigem -> {
                log.info("Buscando passagens de {} ({}) para {} via Amadeus - Duração: {} dias, {} pessoas", 
                        origem, codigoOrigem, DESTINO_IATA, duracaoDias, numeroPessoas);
                return obterAccessToken()
                    .map(token -> {
                        LocalDate dataIda = LocalDate.now().plusDays(DAYS_AHEAD_DEPARTURE);
                        LocalDate dataVolta = dataIda.plusDays(duracaoDias);
                        BigDecimal precoPorPessoa = buscarOfertasVoos(token, codigoOrigem, dataIda, dataVolta);
                        if (precoPorPessoa != null && precoPorPessoa.compareTo(BigDecimal.ZERO) > 0) {
                            BigDecimal precoTotal = precoPorPessoa.multiply(BigDecimal.valueOf(numeroPessoas))
                                    .setScale(2, RoundingMode.HALF_UP);
                            log.info("Preço por pessoa: R$ {}, Total para {} pessoas: R$ {}", 
                                    precoPorPessoa, numeroPessoas, precoTotal);
                            return precoTotal;
                        }
                        return null;
                    })
                    .orElseGet(() -> {
                        log.error("Não foi possível obter token de acesso da Amadeus");
                        return null;
                    });
            })
            .filter(preco -> preco != null && preco.compareTo(BigDecimal.ZERO) > 0)
            .orElseGet(() -> {
                log.warn("Nenhuma oferta encontrada ou preço inválido. Usando valor estimado.");
                return calcularPrecoEstimado(origem, numeroPessoas);
            });
    }

    private Optional<String> obterAccessToken() {
        if (isTokenValid()) {
            return Optional.of(accessToken);
        }

        try {
            HttpHeaders headers = createAuthHeaders();
            HttpEntity<MultiValueMap<String, String>> request = createAuthRequest(headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(authUrl, request, Map.class);

            return extractTokenFromResponse(response)
                .map(tokenData -> {
                    accessToken = tokenData.token();
                    tokenExpirationTime = System.currentTimeMillis() + (tokenData.expiresIn() * 1000L);
                    log.info("Token de acesso obtido com sucesso. Expira em {} segundos", tokenData.expiresIn());
                    return accessToken;
                });
        } catch (Exception e) {
            log.error("Erro ao obter token de acesso: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    private boolean isTokenValid() {
        return accessToken != null 
            && System.currentTimeMillis() < tokenExpirationTime - TOKEN_REFRESH_MARGIN_MS;
    }

    private HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private HttpEntity<MultiValueMap<String, String>> createAuthRequest(HttpHeaders headers) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", apiKey);
        body.add("client_secret", apiSecret);
        return new HttpEntity<>(body, headers);
    }

    private Optional<TokenData> extractTokenFromResponse(ResponseEntity<Map> response) {
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            log.error("Resposta inválida ao obter token: {}", response.getBody());
            return Optional.empty();
        }

        Map<String, Object> responseBody = response.getBody();
        String token = (String) responseBody.get("access_token");
        Integer expiresIn = (Integer) responseBody.get("expires_in");

        if (token != null && expiresIn != null) {
            return Optional.of(new TokenData(token, expiresIn));
        }

        return Optional.empty();
    }

    private BigDecimal buscarOfertasVoos(String token, String origem, LocalDate dataIda, LocalDate dataVolta) {
        try {
            HttpHeaders headers = createFlightSearchHeaders(token);
            Map<String, Object> requestBody = buildFlightSearchRequest(origem, dataIda, dataVolta);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            log.debug("Buscando ofertas de voos: {} -> {} em {}", origem, DESTINO_IATA, dataIda);

            ResponseEntity<Map> response = restTemplate.postForEntity(apiBaseUrl, request, Map.class);

            return extractPriceFromResponse(response)
                .orElse(null);

        } catch (ResourceAccessException e) {
            log.warn("Timeout ou erro de conexão ao buscar ofertas de voos na Amadeus: {}", e.getMessage());
            return null;
        } catch (HttpClientErrorException e) {
            log.error("Erro HTTP ao buscar ofertas de voos: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            log.error("Erro ao buscar ofertas de voos: {}", e.getMessage(), e);
            return null;
        }
    }

    private HttpHeaders createFlightSearchHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private Map<String, Object> buildFlightSearchRequest(String origem, LocalDate dataIda, LocalDate dataVolta) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("originDestinations", buildOriginDestinations(origem, dataIda, dataVolta));
        requestBody.put("travelers", buildTravelers());
        requestBody.put("sources", List.of(SOURCE_GDS));
        requestBody.put("currencyCode", CURRENCY_BRL);
        requestBody.put("max", MAX_RESULTS);
        return requestBody;
    }

    private List<Map<String, Object>> buildOriginDestinations(String origem, LocalDate dataIda, LocalDate dataVolta) {
        return List.of(
            buildOriginDestination("1", origem, DESTINO_IATA, dataIda),
            buildOriginDestination("2", DESTINO_IATA, origem, dataVolta)
        );
    }

    private Map<String, Object> buildOriginDestination(String id, String origem, String destino, LocalDate data) {
        Map<String, Object> destination = new HashMap<>();
        destination.put("id", id);
        destination.put("originLocationCode", origem);
        destination.put("destinationLocationCode", destino);
        
        Map<String, Object> departureRange = new HashMap<>();
        departureRange.put("date", data.format(DATE_FORMATTER));
        destination.put("departureDateTimeRange", departureRange);
        
        return destination;
    }

    private List<Map<String, Object>> buildTravelers() {
        Map<String, Object> traveler = new HashMap<>();
        traveler.put("id", "1");
        traveler.put("travelerType", TRAVELER_TYPE_ADULT);
        traveler.put("quantity", 1);
        return List.of(traveler);
    }

    @SuppressWarnings("unchecked")
    private Optional<BigDecimal> extractPriceFromResponse(ResponseEntity<Map> response) {
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            log.warn("Resposta inválida da Amadeus: status={}, body={}", 
                    response.getStatusCode(), response.getBody());
            return Optional.empty();
        }

        Map<String, Object> responseBody = response.getBody();
        List<Map<String, Object>> data = (List<Map<String, Object>>) responseBody.get("data");

        if (data == null || data.isEmpty()) {
            log.warn("Nenhuma oferta encontrada na resposta da Amadeus");
            return Optional.empty();
        }

        return extractPriceFromOffer(data.get(0));
    }

    @SuppressWarnings("unchecked")
    private Optional<BigDecimal> extractPriceFromOffer(Map<String, Object> offer) {
        Map<String, Object> price = (Map<String, Object>) offer.get("price");
        
        if (price == null) {
            log.warn("Resposta da Amadeus não contém preço na oferta");
            return Optional.empty();
        }

        Object totalObj = Optional.ofNullable(price.get("grandTotal"))
            .orElse(price.get("total"));

        return Optional.ofNullable(extractPrice(totalObj))
            .map(total -> {
                BigDecimal precoPorPessoa = BigDecimal.valueOf(total)
                    .setScale(2, RoundingMode.HALF_UP);
                log.debug("Preço por pessoa encontrado na Amadeus: R$ {}", precoPorPessoa);
                return precoPorPessoa;
            });
    }

    private BigDecimal calcularPrecoEstimado(String origem, Integer numeroPessoas) {
        String codigoIATA = CapitalBrasileira.extrairCodigoIATA(origem);
        double multiplicador = calcularMultiplicadorPorOrigem(origem, codigoIATA);
        double precoPorPessoa = fallbackPricePerPerson * multiplicador;
        double total = precoPorPessoa * numeroPessoas;

        return BigDecimal.valueOf(total)
            .setScale(2, RoundingMode.HALF_UP);
    }

    private double calcularMultiplicadorPorOrigem(String origem, String codigoIATA) {
        String origemUpper = origem.toUpperCase();

        if (isRecife(origemUpper, codigoIATA)) {
            return MULTIPLIER_RECIFE;
        }

        if (isNordeste(origemUpper)) {
            return MULTIPLIER_NORDESTE;
        }

        if (isSudeste(origemUpper)) {
            return MULTIPLIER_SUDESTE;
        }

        if (isSul(origemUpper)) {
            return MULTIPLIER_SUL;
        }

        return MULTIPLIER_CENTRO_OESTE_NORTE;
    }

    private boolean isRecife(String origemUpper, String codigoIATA) {
        return origemUpper.contains("RECIFE") 
            || origemUpper.contains("PE") 
            || "REC".equals(codigoIATA);
    }

    private boolean isNordeste(String origemUpper) {
        return origemUpper.contains("NORDESTE")
            || origemUpper.contains("SALVADOR") || origemUpper.contains("BA")
            || origemUpper.contains("NATAL") || origemUpper.contains("RN")
            || origemUpper.contains("FORTALEZA") || origemUpper.contains("CE")
            || origemUpper.contains("MACEIÓ") || origemUpper.contains("AL")
            || origemUpper.contains("ARACAJU") || origemUpper.contains("SE")
            || origemUpper.contains("JOÃO PESSOA") || origemUpper.contains("PB")
            || origemUpper.contains("SÃO LUÍS") || origemUpper.contains("MA")
            || origemUpper.contains("TERESINA") || origemUpper.contains("PI");
    }

    private boolean isSudeste(String origemUpper) {
        return origemUpper.contains("SÃO PAULO") || origemUpper.contains("SP")
            || origemUpper.contains("RIO") || origemUpper.contains("RJ")
            || origemUpper.contains("BELO HORIZONTE") || origemUpper.contains("MG")
            || origemUpper.contains("VITÓRIA") || origemUpper.contains("ES");
    }

    private boolean isSul(String origemUpper) {
        return origemUpper.contains("CURITIBA") || origemUpper.contains("PR")
            || origemUpper.contains("FLORIANÓPOLIS") || origemUpper.contains("SC")
            || origemUpper.contains("PORTO ALEGRE") || origemUpper.contains("RS");
    }

    private Double extractPrice(Object priceObj) {
        if (priceObj == null) {
            return null;
        }
        if (priceObj instanceof Number) {
            return ((Number) priceObj).doubleValue();
        }
        if (priceObj instanceof String) {
            try {
                return Double.parseDouble((String) priceObj);
            } catch (NumberFormatException e) {
                log.warn("Não foi possível converter preço: {}", priceObj);
                return null;
            }
        }
        return null;
    }

    private record TokenData(String token, Integer expiresIn) {}
}
