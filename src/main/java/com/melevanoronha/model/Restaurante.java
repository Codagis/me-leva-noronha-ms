package com.melevanoronha.model;

import com.melevanoronha.enumerator.CategoriaRestaurante;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

/**
 * Entidade responsável por armazenar os restaurantes disponíveis, incluindo
 * informações de contato, categoria e imagem ilustrativa.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "restaurante",
        indexes = {
                @Index(name = "idx_restaurante_nome", columnList = "nome"),
                @Index(name = "idx_restaurante_categoria", columnList = "categoria")
        })
@Getter
@Setter
@NoArgsConstructor
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "descricao", nullable = false, length = 1000)
    private String descricao;

    @NotBlank
    @Size(max = 20)
    @Column(name = "numero_whatsapp", nullable = false, length = 20)
    private String numeroWhatsapp;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "categoria", nullable = false, length = 20)
    private CategoriaRestaurante categoria;

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

