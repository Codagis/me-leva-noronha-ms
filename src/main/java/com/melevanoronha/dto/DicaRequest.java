package com.melevanoronha.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DicaRequest {

    @NotBlank
    @Size(max = 100)
    private String tag;

    @NotBlank
    @Size(max = 150)
    private String titulo;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotBlank
    @Size(max = 255)
    private String linkWhatsapp;

    @NotNull
    private MultipartFile imagem;

    @NotNull
    private MultipartFile icone;
}

