package com.melevanoronha.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO de requisição para cadastro de dicas, incluindo arquivos de mídia.
 *
 * @param tag identificador amigável da dica
 * @param titulo título da dica
 * @param descricao descrição detalhada
 * @param linkWhatsapp link para contato via WhatsApp
 * @param imagem arquivo de imagem principal
 * @param icone arquivo de ícone representativo
 */
public record DicaRequest(
        @NotBlank @Size(max = 100) String tag,
        @NotBlank @Size(max = 150) String titulo,
        @NotBlank @Size(max = 1000) String descricao,
        @NotBlank @Size(max = 255) String linkWhatsapp,
        @NotNull MultipartFile imagem,
        @NotNull MultipartFile icone
) {
}


