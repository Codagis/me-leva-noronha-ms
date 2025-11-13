package com.melevanoronha.dto.response;

/**
 * DTO de resposta para exposição de dicas no aplicativo,
 * incluindo links para imagem e ícone armazenados no backend.
 *
 * @param id identificador único da dica
 * @param tag rótulo amigável
 * @param titulo título a ser exibido
 * @param descricao descrição detalhada
 * @param linkWhatsapp link de contato via WhatsApp
 * @param linkImagem endpoint para recuperar a imagem
 * @param linkIcone endpoint para recuperar o ícone
 */
public record DicaResponse(
        Long id,
        String tag,
        String titulo,
        String descricao,
        String linkWhatsapp,
        String linkImagem,
        String linkIcone
) {

    public DicaResponse withLinkImagem(String linkImagem) {
        return new DicaResponse(id, tag, titulo, descricao, linkWhatsapp, linkImagem, linkIcone);
    }

    public DicaResponse withLinkIcone(String linkIcone) {
        return new DicaResponse(id, tag, titulo, descricao, linkWhatsapp, linkImagem, linkIcone);
    }
}

