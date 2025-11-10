package com.melevanoronha.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidade responsável por armazenar tokens de refresh para autenticação JWT.
 * Permite renovação de tokens de acesso sem necessidade de novo login.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Entity
@Table(name = "refresh_tokens",
       indexes = {
           @Index(name = "idx_refresh_token_token", columnList = "token"),
           @Index(name = "idx_refresh_token_usuario", columnList = "usuario_id"),
           @Index(name = "idx_refresh_token_expires_at", columnList = "expires_at")
       },
       uniqueConstraints = {
           @UniqueConstraint(name = "uk_refresh_token_token", columnNames = "token")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false, unique = true, columnDefinition = "TEXT")
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "revoked")
    private Boolean revoked = false;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (revoked == null) {
            revoked = false;
        }
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isValid() {
        return !revoked && !isExpired();
    }
}





