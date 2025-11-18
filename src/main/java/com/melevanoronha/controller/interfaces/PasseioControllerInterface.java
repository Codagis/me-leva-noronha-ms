package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.PasseioRequest;
import com.melevanoronha.dto.PasseioResponse;
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
 * Interface do controller REST responsável pelos endpoints de consulta e cadastro de passeios,
 * incluindo o fornecimento da imagem armazenada no banco de dados.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Passeios", description = "Endpoints para gerenciamento de passeios em Fernando de Noronha")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/passeios")
public interface PasseioControllerInterface {

    @Operation(
            summary = "Listar todos os passeios",
            description = "Retorna a lista completa de todos os passeios cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de passeios retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = PasseioResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping
    ResponseEntity<List<PasseioResponse>> listar();

    @Operation(
            summary = "Buscar passeio por ID",
            description = "Retorna os detalhes de um passeio específico pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Passeio encontrado",
                    content = @Content(schema = @Schema(implementation = PasseioResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passeio não encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<PasseioResponse> buscarPorId(@PathVariable Long id);

    @Operation(
            summary = "Cadastrar novo passeio",
            description = "Cria um novo passeio no sistema. Requer upload de imagem em formato multipart/form-data."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Passeio criado com sucesso",
                    content = @Content(schema = @Schema(implementation = PasseioResponse.class))
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
    ResponseEntity<PasseioResponse> cadastrar(@Valid @ModelAttribute PasseioRequest request);

    @Operation(
            summary = "Obter imagem do passeio",
            description = "Retorna a imagem associada a um passeio específico."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Imagem retornada com sucesso",
                    content = @Content(mediaType = "image/*")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passeio ou imagem não encontrada",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping("/{id}/imagem")
    ResponseEntity<byte[]> obterImagem(@PathVariable Long id);
}

