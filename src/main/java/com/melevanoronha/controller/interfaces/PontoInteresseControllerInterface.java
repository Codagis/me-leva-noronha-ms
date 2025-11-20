package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.request.PontoInteresseRequest;
import com.melevanoronha.dto.response.PontoInteresseResponse;
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
 * Interface do controller REST responsável pelos endpoints de consulta e cadastro de pontos de interesse.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Pontos de Interesse", description = "Endpoints para gerenciamento de pontos de interesse em Fernando de Noronha")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/pontos-interesse")
public interface PontoInteresseControllerInterface {

    @Operation(
            summary = "Listar todos os pontos de interesse",
            description = "Retorna a lista completa de todos os pontos de interesse cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de pontos de interesse retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = PontoInteresseResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping
    ResponseEntity<List<PontoInteresseResponse>> listar();

    @Operation(
            summary = "Cadastrar novo ponto de interesse",
            description = "Cria um novo ponto de interesse no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Ponto de interesse criado com sucesso",
                    content = @Content(schema = @Schema(implementation = PontoInteresseResponse.class))
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
    @PostMapping
    ResponseEntity<PontoInteresseResponse> cadastrar(@Valid @RequestBody PontoInteresseRequest request);
}
