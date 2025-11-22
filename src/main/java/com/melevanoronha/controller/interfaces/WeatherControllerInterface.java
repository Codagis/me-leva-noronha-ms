package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.response.WeatherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface do controller REST responsável pelos endpoints de previsão do tempo.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Previsão do Tempo", description = "Endpoints para consulta de previsão do tempo em Fernando de Noronha (requer autenticação)")
@RequestMapping("/api/weather")
public interface WeatherControllerInterface {

    @Operation(
            summary = "Obter previsão completa do tempo",
            description = "Retorna a previsão completa do tempo para Fernando de Noronha. " +
                    "Se latitude e longitude não forem informados, utiliza as coordenadas padrão de Fernando de Noronha."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Previsão retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = WeatherResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro ao consultar serviço de previsão do tempo",
                    content = @Content
            )
    })
    @GetMapping("/forecast")
    ResponseEntity<WeatherResponse> obterPrevisaoCompleta(
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    );

    @Operation(
            summary = "Obter condições atuais do tempo",
            description = "Retorna as condições atuais do tempo para Fernando de Noronha. " +
                    "Se latitude e longitude não forem informados, utiliza as coordenadas padrão de Fernando de Noronha."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Condições atuais retornadas com sucesso",
                    content = @Content(schema = @Schema(implementation = WeatherResponse.CurrentWeather.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro ao consultar serviço de previsão do tempo",
                    content = @Content
            )
    })
    @GetMapping("/current")
    ResponseEntity<WeatherResponse.CurrentWeather> obterDadosAtuais(
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    );
}

