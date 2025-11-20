package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.request.RestauranteRequest;
import com.melevanoronha.dto.response.RestauranteResponse;
import com.melevanoronha.enumerator.CategoriaRestaurante;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Interface do controller REST responsável pelos endpoints de consulta e cadastro de restaurantes,
 * incluindo o fornecimento da imagem armazenada no banco de dados.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Restaurantes", description = "Endpoints para gerenciamento de restaurantes em Fernando de Noronha")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/restaurantes")
public interface RestauranteControllerInterface {

    @Operation(
            summary = "Listar restaurantes",
            description = "Retorna a lista de restaurantes cadastrados no sistema. Pode ser filtrada por categoria usando o parâmetro 'categoria'. Valores possíveis: ECONOMICO ($), MODERADO ($$), SOFISTICADO ($$$), PREMIUM ($$$$)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de restaurantes retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = RestauranteResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Categoria inválida",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping
    ResponseEntity<List<RestauranteResponse>> listar(
            @Parameter(description = "Categoria do restaurante para filtro (ECONOMICO, MODERADO, SOFISTICADO, PREMIUM)", required = false)
            @RequestParam(required = false) CategoriaRestaurante categoria
    );

    @Operation(
            summary = "Buscar restaurante por ID",
            description = "Retorna os detalhes de um restaurante específico pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Restaurante encontrado",
                    content = @Content(schema = @Schema(implementation = RestauranteResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<RestauranteResponse> buscarPorId(@PathVariable Long id);

    @Operation(
            summary = "Cadastrar novo restaurante",
            description = "Cria um novo restaurante no sistema. Requer upload de imagem em formato multipart/form-data."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Restaurante criado com sucesso",
                    content = @Content(schema = @Schema(implementation = RestauranteResponse.class))
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
    ResponseEntity<RestauranteResponse> cadastrar(@Valid @ModelAttribute RestauranteRequest request);

    @Operation(
            summary = "Obter imagem do restaurante",
            description = "Retorna a imagem associada a um restaurante específico."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Imagem retornada com sucesso",
                    content = @Content(mediaType = "image/*")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurante ou imagem não encontrada",
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

