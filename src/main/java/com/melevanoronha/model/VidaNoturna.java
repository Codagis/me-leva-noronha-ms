package com.melevanoronha.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

/**
 * Entidade responsável por armazenar opções de vida noturna, incluindo horários,
 * contatos e imagem ilustrativa para exibição no aplicativo.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "vida_noturna",
        indexes = {
                @Index(name = "idx_vida_noturna_titulo", columnList = "titulo"),
                @Index(name = "idx_vida_noturna_whatsapp", columnList = "numero_whatsapp")
        })
@Getter
@Setter
@NoArgsConstructor
public class VidaNoturna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Size(max = 1000)
    @Column(name = "descricao", length = 1000)
    private String descricao;

    @Size(max = 255)
    @Column(name = "destaque", length = 255)
    private String destaque;

    @NotBlank
    @Size(max = 255)
    @Column(name = "horario_funcionamento", nullable = false, length = 255)
    private String horarioFuncionamento;

    @NotBlank
    @Size(max = 30)
    @Column(name = "numero_whatsapp", nullable = false, length = 30)
    private String numeroWhatsapp;

    @NotBlank
    @Size(max = 512)
    @Column(name = "link_google_maps", nullable = false, length = 512)
    private String linkGoogleMaps;

    @Column(name = "link_imagem", nullable = false, length = 512)
    private String linkImagem;

    @NotBlank
    @Size(max = 100)
    @Column(name = "imagem_content_type", nullable = false, length = 100)
    private String imagemContentType;

    @Lob
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "imagem_dados", nullable = false)
    private byte[] imagemDados;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}


