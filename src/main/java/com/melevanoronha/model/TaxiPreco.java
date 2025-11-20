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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidade responsável por armazenar os preços de táxi entre diferentes locais de Fernando de Noronha.
 * Cada registro contém uma origem, um destino e dois valores (Tabela 1 e Tabela 2).
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "taxi_preco",
        indexes = {
                @Index(name = "idx_taxi_origem", columnList = "origem"),
                @Index(name = "idx_taxi_destino", columnList = "destino"),
                @Index(name = "idx_taxi_origem_destino", columnList = "origem,destino")
        })
@Getter
@Setter
@NoArgsConstructor
public class TaxiPreco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "origem", nullable = false, length = 150)
    private String origem;

    @NotBlank
    @Size(max = 150)
    @Column(name = "destino", nullable = false, length = 150)
    private String destino;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    @Column(name = "valor_tabela_1", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTabela1;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    @Column(name = "valor_tabela_2", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTabela2;

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

