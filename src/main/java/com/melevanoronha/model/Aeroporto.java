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
 * Entidade responsável por armazenar informações sobre aeroportos brasileiros
 * utilizados para cálculo de passagens aéreas.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "aeroporto",
        indexes = {
                @Index(name = "idx_aeroporto_codigo_iata", columnList = "codigo_iata"),
                @Index(name = "idx_aeroporto_cidade", columnList = "cidade")
        })
@Getter
@Setter
@NoArgsConstructor
public class Aeroporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "cidade", nullable = false, length = 200)
    private String cidade;

    @NotBlank
    @Size(max = 300)
    @Column(name = "nome_aeroporto", nullable = false, length = 300)
    private String nomeAeroporto;

    @NotBlank
    @Size(max = 3)
    @Column(name = "codigo_iata", nullable = false, unique = true, length = 3)
    private String codigoIATA;

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

