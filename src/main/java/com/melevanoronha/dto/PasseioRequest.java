package com.melevanoronha.dto;

import com.melevanoronha.enumerator.PasseioCategoria;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO utilizado para receber os dados de cadastro de passeios via formulário multipart,
 * contendo informações textuais, categoria e imagem ilustrativa.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public record PasseioRequest(
        @NotBlank
        @Size(max = 100)
        String tag,

        @NotBlank
        @Size(max = 150)
        String titulo,

        @NotBlank
        @Size(max = 1000)
        String descricao,

        @NotBlank
        @Size(max = 100)
        String duracao,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer = 10, fraction = 2)
        BigDecimal valor,

        @NotEmpty
        List<@NotBlank @Size(max = 255) String> itensIncluidos,

        @NotBlank
        @Size(max = 255)
        String linkWhatsapp,

        @NotNull
        PasseioCategoria categoria,

        @NotNull
        MultipartFile imagem
) {
}


