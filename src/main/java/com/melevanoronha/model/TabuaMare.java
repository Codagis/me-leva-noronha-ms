package com.melevanoronha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidade responsável por armazenar registros de marés, com horários e alturas
 * associados a cada data disponível para consulta no sistema.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "tabuamare",
       indexes = {
           @Index(name = "idx_tabuamare_data", columnList = "data"),
           @Index(name = "idx_tabuamare_data_horario", columnList = "data, horario")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TabuaMare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @NotBlank(message = "Horário é obrigatório")
    @Pattern(regexp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$", message = "Horário deve estar no formato HH:mm (ex: 09:30, 03:45)")
    @Size(max = 5, message = "Horário deve ter no máximo 5 caracteres")
    @Column(name = "horario", nullable = false, length = 5)
    private String horario;

    @NotBlank(message = "Metro é obrigatório")
    @Size(max = 10, message = "Metro deve ter no máximo 10 caracteres")
    @Column(name = "metro", nullable = false, length = 10)
    private String metro;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabuaMare that = (TabuaMare) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

