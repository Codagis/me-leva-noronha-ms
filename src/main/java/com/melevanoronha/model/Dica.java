package com.melevanoronha.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@Entity
@Table(name = "dica")
@Getter
@Setter
@NoArgsConstructor
public class Dica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "tag", nullable = false, length = 100)
    private String tag;

    @NotBlank
    @Size(max = 150)
    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "descricao", nullable = false, length = 1000)
    private String descricao;

    @NotBlank
    @Size(max = 255)
    @Column(name = "link_whatsapp", nullable = false, length = 255)
    private String linkWhatsapp;

    @Column(name = "link_imagem", nullable = false, length = 512)
    private String linkImagem;

    @Column(name = "link_icone", nullable = false, length = 512)
    private String linkIcone;

    @NotBlank
    @Size(max = 100)
    @Column(name = "imagem_content_type", nullable = false, length = 100)
    private String imagemContentType;

    @NotBlank
    @Size(max = 100)
    @Column(name = "icone_content_type", nullable = false, length = 100)
    private String iconeContentType;

    @Lob
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "imagem_dados", nullable = false)
    private byte[] imagemDados;

    @Lob
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "icone_dados", nullable = false)
    private byte[] iconeDados;

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

