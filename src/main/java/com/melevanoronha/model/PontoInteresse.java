package com.melevanoronha.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidade responsável por armazenar os pontos de interesse em Fernando de Noronha,
 * incluindo título, categoria, tag e link do Google Maps.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "ponto_interesse",
        indexes = {
                @Index(name = "idx_ponto_interesse_titulo", columnList = "titulo"),
                @Index(name = "idx_ponto_interesse_categoria", columnList = "categoria"),
                @Index(name = "idx_ponto_interesse_tag", columnList = "tag")
        })
@Getter
@Setter
@NoArgsConstructor
public class PontoInteresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @NotBlank
    @Size(max = 100)
    @Column(name = "categoria", nullable = false, length = 100)
    private String categoria;

    @NotBlank
    @Size(max = 100)
    @Column(name = "tag", nullable = false, length = 100)
    private String tag;

    @NotBlank
    @Size(max = 512)
    @Column(name = "link_google_maps", nullable = false, length = 512)
    private String linkGoogleMaps;

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
