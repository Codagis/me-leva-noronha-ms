package com.melevanoronha.enumerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Enum que mapeia as capitais brasileiras para seus códigos IATA de aeroportos.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public enum CapitalBrasileira {
    RIO_BRANCO_AC("Rio Branco - AC", "RBR"),
    MACEIO_AL("Maceió - AL", "MCZ"),
    MACAPA_AP("Macapá - AP", "MCP"),
    MANAUS_AM("Manaus - AM", "MAO"),
    SALVADOR_BA("Salvador - BA", "SSA"),
    FORTALEZA_CE("Fortaleza - CE", "FOR"),
    BRASILIA_DF("Brasília - DF", "BSB"),
    VITORIA_ES("Vitória - ES", "VIX"),
    GOIANIA_GO("Goiânia - GO", "GYN"),
    SAO_LUIS_MA("São Luís - MA", "SLZ"),
    CUIABA_MT("Cuiabá - MT", "CGB"),
    CAMPO_GRANDE_MS("Campo Grande - MS", "CGR"),
    BELO_HORIZONTE_MG("Belo Horizonte - MG", "CNF"),
    BELEM_PA("Belém - PA", "BEL"),
    JOAO_PESSOA_PB("João Pessoa - PB", "JPA"),
    CURITIBA_PR("Curitiba - PR", "CWB"),
    RECIFE_PE("Recife - PE", "REC"),
    TERESINA_PI("Teresina - PI", "THE"),
    RIO_DE_JANEIRO_RJ("Rio de Janeiro - RJ", "GIG"), // Usando Galeão como principal
    NATAL_RN("Natal - RN", "NAT"),
    PORTO_ALEGRE_RS("Porto Alegre - RS", "POA"),
    PORTO_VELHO_RO("Porto Velho - RO", "PVH"),
    BOA_VISTA_RR("Boa Vista - RR", "BVB"),
    FLORIANOPOLIS_SC("Florianópolis - SC", "FLN"),
    SAO_PAULO_SP("São Paulo - SP", "GRU"), // Usando Guarulhos como principal
    ARACAJU_SE("Aracaju - SE", "AJU"),
    PALMAS_TO("Palmas - TO", "PMW");

    private final String nomeCompleto;
    private final String codigoIATA;

    CapitalBrasileira(String nomeCompleto, String codigoIATA) {
        this.nomeCompleto = nomeCompleto;
        this.codigoIATA = codigoIATA;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCodigoIATA() {
        return codigoIATA;
    }

    private static final Map<String, CapitalBrasileira> MAPA_POR_NOME = new HashMap<>();
    private static final Map<String, CapitalBrasileira> MAPA_POR_CIDADE = new HashMap<>();

    static {
        for (CapitalBrasileira capital : values()) {
            MAPA_POR_NOME.put(capital.nomeCompleto.toUpperCase(), capital);
            // Mapeia também apenas a cidade (sem UF)
            String cidade = capital.nomeCompleto.split(" - ")[0].toUpperCase();
            MAPA_POR_CIDADE.put(cidade, capital);
        }
    }

    /**
     * Busca uma capital pelo nome completo no formato "Cidade - UF".
     * 
     * @param nomeCompleto nome no formato "Fortaleza - CE"
     * @return Optional com a capital encontrada
     */
    public static Optional<CapitalBrasileira> buscarPorNomeCompleto(String nomeCompleto) {
        if (nomeCompleto == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(MAPA_POR_NOME.get(nomeCompleto.toUpperCase().trim()));
    }

    /**
     * Busca uma capital pelo nome da cidade (sem UF).
     * 
     * @param cidade nome da cidade (ex: "Fortaleza")
     * @return Optional com a capital encontrada
     */
    public static Optional<CapitalBrasileira> buscarPorCidade(String cidade) {
        if (cidade == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(MAPA_POR_CIDADE.get(cidade.toUpperCase().trim()));
    }

    /**
     * Extrai o código IATA de uma string no formato "Cidade - UF".
     * 
     * @param origem string no formato "Fortaleza - CE"
     * @return código IATA ou null se não encontrado
     */
    public static String extrairCodigoIATA(String origem) {
        if (origem == null || origem.isBlank()) {
            return null;
        }

        // Tenta buscar pelo nome completo primeiro
        Optional<CapitalBrasileira> capital = buscarPorNomeCompleto(origem);
        if (capital.isPresent()) {
            return capital.get().getCodigoIATA();
        }

        // Se não encontrar, tenta buscar apenas pela cidade
        String cidade = origem.split(" - ")[0].trim();
        capital = buscarPorCidade(cidade);
        if (capital.isPresent()) {
            return capital.get().getCodigoIATA();
        }

        return null;
    }
}

