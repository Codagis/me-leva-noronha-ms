package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.request.CalculadoraPassagensRequest;
import com.melevanoronha.dto.request.CalculadoraViagemRequest;
import com.melevanoronha.dto.response.CalculadoraPassagensResponse;
import com.melevanoronha.dto.response.CalculadoraViagemResponse;
import com.melevanoronha.dto.response.CapitalResponse;
import com.melevanoronha.dto.response.PasseioCalculadoraResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Interface do controller REST responsável pelo endpoint de cálculo de custos de viagem.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Calculadora de Viagem", description = "Endpoints para cálculo de custos de viagem para Fernando de Noronha")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/calculadora-viagem")
public interface CalculadoraViagemControllerInterface {

    @Operation(
            summary = "Calcular passagens aéreas",
            description = "Calcula apenas o valor das passagens aéreas para Fernando de Noronha. " +
                    "Utiliza a API Amadeus quando configurada, caso contrário retorna valor estimado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cálculo realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = CalculadoraPassagensResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @PostMapping("/passagens")
    ResponseEntity<CalculadoraPassagensResponse> calcularPassagens(
            @Valid @RequestBody CalculadoraPassagensRequest request
    );

    @Operation(
            summary = "Calcular custos (sem passagens)",
            description = "Calcula os custos da viagem EXCETO passagens aéreas. " +
                    "Inclui hospedagem, alimentação, passeios, transporte local e taxas. " +
                    "O campo 'passagensAereas' sempre retornará R$ 0,00."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cálculo realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = CalculadoraViagemResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @PostMapping("/calcular")
    ResponseEntity<CalculadoraViagemResponse> calcularCustos(
            @Valid @RequestBody CalculadoraViagemRequest request
    );

    @Operation(
            summary = "Calcular outros custos",
            description = "Calcula apenas os outros custos (sem passagens aéreas). " +
                    "Equivalente ao endpoint /calcular. Inclui hospedagem, alimentação, passeios, " +
                    "transporte local e taxas. O campo 'passagensAereas' sempre retornará R$ 0,00."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cálculo realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = CalculadoraViagemResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @PostMapping("/calcular-outros")
    ResponseEntity<CalculadoraViagemResponse> calcularCustosOutros(
            @Valid @RequestBody CalculadoraViagemRequest request
    );

    @Operation(
            summary = "Calcular custos completos",
            description = "Calcula TODOS os custos da viagem incluindo passagens aéreas. " +
                    "Inclui passagens (via API Amadeus), hospedagem, alimentação, passeios, " +
                    "transporte local e taxas. Respeita o flag 'jaTemPassagens'."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cálculo realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = CalculadoraViagemResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @PostMapping("/calcular-completo")
    ResponseEntity<CalculadoraViagemResponse> calcularCustosCompleto(
            @Valid @RequestBody CalculadoraViagemRequest request
    );

    @Operation(
            summary = "Listar capitais brasileiras",
            description = "Retorna a lista de todas as 26 capitais brasileiras disponíveis como origem da viagem. " +
                    "Cada capital inclui o nome completo no formato 'Cidade - UF' e o código IATA do aeroporto."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de capitais retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = CapitalResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping("/capitais")
    ResponseEntity<List<CapitalResponse>> listarCapitais();

    @Operation(
            summary = "Listar passeios disponíveis",
            description = "Retorna a lista de todos os passeios disponíveis na calculadora de viagem. " +
                    "Cada passeio inclui o código (usado na requisição de cálculo), nome amigável e valor por pessoa."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de passeios retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = PasseioCalculadoraResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping("/passeios")
    ResponseEntity<List<PasseioCalculadoraResponse>> listarPasseios();
}

