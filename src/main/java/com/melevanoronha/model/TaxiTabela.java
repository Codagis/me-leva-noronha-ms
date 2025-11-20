package com.melevanoronha.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
 * Entidade responsável por armazenar as informações das tabelas de táxi (Tabela 1 e Tabela 2).
 * Cada tabela possui um número de WhatsApp associado.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "taxi_tabela")
@Getter
@Setter
@NoArgsConstructor
public class TaxiTabela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nome", nullable = false, unique = true, length = 50)
    private String nome; // "Tabela 1" ou "Tabela 2"

    @NotBlank
    @Size(max = 20)
    @Column(name = "numero_whatsapp", nullable = false, length = 20)
    private String numeroWhatsapp;

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

