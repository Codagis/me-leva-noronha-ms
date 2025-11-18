package com.melevanoronha.model;

import com.melevanoronha.enumerator.PasseioCategoria;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade responsável por armazenar os passeios disponíveis, incluindo mídias,
 * descrição detalhada, itens inclusos e categoria de experiência.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "passeio",
        indexes = {
                @Index(name = "idx_passeio_tag", columnList = "tag"),
                @Index(name = "idx_passeio_titulo", columnList = "titulo"),
                @Index(name = "idx_passeio_categoria", columnList = "categoria")
        })
@Getter
@Setter
@NoArgsConstructor
public class Passeio {

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
    @Size(max = 100)
    @Column(name = "duracao", nullable = false, length = 100)
    private String duracao;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "passeio_inclusoes", joinColumns = @JoinColumn(name = "passeio_id"))
    @Column(name = "item_incluso", nullable = false, length = 255)
    private List<@NotBlank @Size(max = 255) String> itensIncluidos = new ArrayList<>();

    @NotBlank
    @Size(max = 255)
    @Column(name = "link_whatsapp", nullable = false, length = 255)
    private String linkWhatsapp;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "categoria", nullable = false, length = 20)
    private PasseioCategoria categoria;

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


