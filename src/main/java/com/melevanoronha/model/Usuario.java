package com.melevanoronha.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidade responsável pelo gerenciamento de usuários do sistema Me Leva Noronha.
 * Representa os dados principais de autenticação e perfil do usuário.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "usuarios", 
       indexes = {
           @Index(name = "idx_usuario_email", columnList = "email"),
           @Index(name = "idx_usuario_username", columnList = "username")
       },
       uniqueConstraints = {
           @UniqueConstraint(name = "uk_usuario_email", columnNames = "email"),
           @UniqueConstraint(name = "uk_usuario_username", columnNames = "username")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Nome de usuário é obrigatório")
    @Size(max = 50, message = "Nome de usuário deve ter no máximo 50 caracteres")
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter formato válido")
    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Column(name = "senha", nullable = false, columnDefinition = "TEXT")
    @JsonIgnore
    private String senha;

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
        Usuario usuario = (Usuario) o;
        return id != null && id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}





