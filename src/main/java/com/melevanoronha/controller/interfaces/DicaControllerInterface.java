package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.request.DicaRequest;
import com.melevanoronha.dto.response.DicaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Interface do controller REST responsável pelos endpoints de consulta e cadastro de dicas.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Dicas", description = "Endpoints para gerenciamento de dicas de viagem para Fernando de Noronha")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/dicas")
public interface DicaControllerInterface {

    @Operation(
            summary = "Listar todas as dicas",
            description = "Retorna a lista completa de todas as dicas cadastradas no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de dicas retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = DicaResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping
    ResponseEntity<List<DicaResponse>> listar();

    @Operation(
            summary = "Buscar dica por ID",
            description = "Retorna os detalhes de uma dica específica pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dica encontrada",
                    content = @Content(schema = @Schema(implementation = DicaResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dica não encontrada",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<DicaResponse> buscarPorId(@PathVariable Long id);

    @Operation(
            summary = "Cadastrar nova dica",
            description = "Cria uma nova dica no sistema. Requer upload de imagem e ícone em formato multipart/form-data."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Dica criada com sucesso",
                    content = @Content(schema = @Schema(implementation = DicaResponse.class))
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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<DicaResponse> cadastrar(@Valid @ModelAttribute DicaRequest request);

    @Operation(
            summary = "Obter imagem da dica",
            description = "Retorna a imagem associada a uma dica específica."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Imagem retornada com sucesso",
                    content = @Content(mediaType = "image/*")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dica ou imagem não encontrada",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping(value = "/{id}/imagem")
    ResponseEntity<byte[]> obterImagem(@PathVariable Long id);

    @Operation(
            summary = "Obter ícone da dica",
            description = "Retorna o ícone associado a uma dica específica."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ícone retornado com sucesso",
                    content = @Content(mediaType = "image/*")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dica ou ícone não encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping(value = "/{id}/icone")
    ResponseEntity<byte[]> obterIcone(@PathVariable Long id);
}

