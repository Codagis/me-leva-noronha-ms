package com.melevanoronha.dto.response;

/**
 * DTO de saída utilizado para expor os dados de vida noturna nos endpoints públicos,
 * consolidando dados textuais e o link da imagem persistida.
 *
 * @param id identificador da opção de vida noturna
 * @param titulo título do estabelecimento/evento
 * @param descricao descrição do estabelecimento/evento
 * @param destaque texto de destaque do estabelecimento/evento
 * @param horarioFuncionamento horários disponíveis
 * @param numeroWhatsapp contato WhatsApp
 * @param linkGoogleMaps link para localização
 * @param linkImagem endpoint para recuperar a imagem
 */
public record VidaNoturnaResponse(
        Long id,
        String titulo,
        String descricao,
        String destaque,
        String horarioFuncionamento,
        String numeroWhatsapp,
        String linkGoogleMaps,
        String linkImagem
) {

    public VidaNoturnaResponse withLinkImagem(String linkImagem) {
        return new VidaNoturnaResponse(id, titulo, descricao, destaque, horarioFuncionamento, numeroWhatsapp, linkGoogleMaps, linkImagem);
    }
}


