package com.melevanoronha.dto.request;

import com.melevanoronha.enumerator.CategoriaRestaurante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO utilizado para receber os dados de cadastro de restaurantes via formulário multipart,
 * contendo informações textuais, categoria e imagem ilustrativa.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public record RestauranteRequest(
        @NotBlank
        @Size(max = 150)
        String nome,

        @NotBlank
        @Size(max = 1000)
        String descricao,

        @NotBlank
        @Size(max = 20)
        String numeroWhatsapp,

        @NotNull
        CategoriaRestaurante categoria,

        @NotNull
        MultipartFile imagem
) {
}

