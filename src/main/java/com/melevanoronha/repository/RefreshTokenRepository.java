package com.melevanoronha.repository;

import com.melevanoronha.model.RefreshToken;
import com.melevanoronha.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository para operações de persistência da entidade RefreshToken.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.token = :token")
    Optional<RefreshToken> findByToken(@Param("token") String token);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.usuario = :usuario")
    Optional<RefreshToken> findByUsuario(@Param("usuario") Usuario usuario);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.usuario.id = :usuarioId")
    List<RefreshToken> findAllByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.usuario.id = :usuarioId AND rt.revoked = false AND rt.expiresAt > :now")
    List<RefreshToken> findValidTokensByUsuarioId(@Param("usuarioId") Long usuarioId, 
                                                   @Param("now") LocalDateTime now);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.expiresAt < :now")
    List<RefreshToken> findExpiredTokens(@Param("now") LocalDateTime now);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.revoked = true")
    List<RefreshToken> findRevokedTokens();

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.revoked = false AND rt.expiresAt > :now")
    List<RefreshToken> findValidTokens(@Param("now") LocalDateTime now);

    @Query("SELECT COUNT(rt) FROM RefreshToken rt WHERE rt.usuario.id = :usuarioId")
    long countTokensByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COUNT(rt) FROM RefreshToken rt WHERE rt.usuario.id = :usuarioId AND rt.revoked = false")
    long countValidTokensByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken rt WHERE rt.expiresAt < :now OR rt.revoked = true")
    void deleteExpiredOrRevokedTokens(@Param("now") LocalDateTime now);

    @Modifying
    @Transactional
    @Query("UPDATE RefreshToken rt SET rt.revoked = true WHERE rt.usuario = :usuario")
    void revokeAllUserTokens(@Param("usuario") Usuario usuario);

    @Modifying
    @Transactional
    @Query("UPDATE RefreshToken rt SET rt.revoked = true WHERE rt.usuario.id = :usuarioId")
    void revokeAllUserTokensById(@Param("usuarioId") Long usuarioId);

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken rt WHERE rt.usuario.id = :usuarioId")
    void deleteAllByUsuarioId(@Param("usuarioId") Long usuarioId);
}


