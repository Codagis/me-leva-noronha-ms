package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.request.TaxiCalculadoraRequest;
import com.melevanoronha.dto.response.TaxiCalculadoraResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface do controller REST responsável pelo endpoint de cálculo de corridas de táxi.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Calculadora de Táxi", description = "Endpoints para cálculo de valores de corridas de táxi em Fernando de Noronha")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/taxi")
public interface TaxiControllerInterface {

    @Operation(
            summary = "Calcular corrida de táxi",
            description = "Calcula o valor de uma corrida de táxi entre dois pontos em Fernando de Noronha. " +
                    "Retorna os valores da Tabela 1 e Tabela 2 para a rota especificada."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cálculo realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = TaxiCalculadoraResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou rota não encontrada",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @PostMapping("/calcular")
    ResponseEntity<TaxiCalculadoraResponse> calcularCorrida(
            @Valid @RequestBody TaxiCalculadoraRequest request
    );
}

