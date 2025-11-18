package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.request.VidaNoturnaRequest;
import com.melevanoronha.dto.response.VidaNoturnaResponse;
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
 * Interface do controller REST que expõe os endpoints de consulta e cadastro de vida noturna,
 * incluindo o fornecimento da imagem armazenada no banco.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Vida Noturna", description = "Endpoints para gerenciamento de estabelecimentos de vida noturna em Fernando de Noronha")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/vida-noturna")
public interface VidaNoturnaControllerInterface {

    @Operation(
            summary = "Listar todos os estabelecimentos de vida noturna",
            description = "Retorna a lista completa de todos os estabelecimentos de vida noturna cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de estabelecimentos retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = VidaNoturnaResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping
    ResponseEntity<List<VidaNoturnaResponse>> listar();

    @Operation(
            summary = "Buscar estabelecimento por ID",
            description = "Retorna os detalhes de um estabelecimento de vida noturna específico pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Estabelecimento encontrado",
                    content = @Content(schema = @Schema(implementation = VidaNoturnaResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Estabelecimento não encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<VidaNoturnaResponse> buscarPorId(@PathVariable Long id);

    @Operation(
            summary = "Cadastrar novo estabelecimento",
            description = "Cria um novo estabelecimento de vida noturna no sistema. Requer upload de imagem em formato multipart/form-data."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Estabelecimento criado com sucesso",
                    content = @Content(schema = @Schema(implementation = VidaNoturnaResponse.class))
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
    ResponseEntity<VidaNoturnaResponse> cadastrar(@Valid @ModelAttribute VidaNoturnaRequest request);

    @Operation(
            summary = "Obter imagem do estabelecimento",
            description = "Retorna a imagem associada a um estabelecimento de vida noturna específico."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Imagem retornada com sucesso",
                    content = @Content(mediaType = "image/*")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Estabelecimento ou imagem não encontrada",
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
}

