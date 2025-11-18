package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.request.TabelaMareRequest;
import com.melevanoronha.dto.response.TabelaMareResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface do controller responsável por gerenciar endpoints relacionados à tabela de marés.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Tabela de Marés", description = "Endpoints para consulta e cadastro de dados de marés em Fernando de Noronha")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/tabuamare")
public interface TabelaMareControllerInterface {

    @Operation(
            summary = "Listar marés por data",
            description = "Retorna os registros de marés para uma data específica. " +
                    "A data deve ser informada no formato ISO (YYYY-MM-DD)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de marés retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = TabelaMareResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Data inválida",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping
    ResponseEntity<List<TabelaMareResponse>> listarPorData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    );

    @Operation(
            summary = "Cadastrar novo registro de maré",
            description = "Cria um novo registro de maré no sistema com data, horário e tipo (cheia ou vazante)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Registro de maré criado com sucesso",
                    content = @Content(schema = @Schema(implementation = TabelaMareResponse.class))
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
    ResponseEntity<TabelaMareResponse> cadastrar(@Valid @RequestBody TabelaMareRequest request);
}

