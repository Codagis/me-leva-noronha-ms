package com.melevanoronha.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO utilizado para receber os dados de cadastro de vida noturna via formulário multipart.
 *
 * @param titulo título da opção de vida noturna
 * @param descricao descrição da opção de vida noturna
 * @param destaque texto de destaque da opção de vida noturna
 * @param horarioFuncionamento horário de funcionamento informado
 * @param numeroWhatsapp contato de WhatsApp
 * @param linkGoogleMaps link para localização no Google Maps
 * @param imagem arquivo de imagem enviado
 */
public record VidaNoturnaRequest(
        @NotBlank @Size(max = 150) String titulo,
        @Size(max = 1000) String descricao,
        @Size(max = 255) String destaque,
        @NotBlank @Size(max = 255) String horarioFuncionamento,
        @NotBlank @Size(max = 30) String numeroWhatsapp,
        @NotBlank @Size(max = 512) String linkGoogleMaps,
        @NotNull MultipartFile imagem
) {
}


