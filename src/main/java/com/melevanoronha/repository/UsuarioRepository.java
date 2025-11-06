package com.melevanoronha.repository;

import com.melevanoronha.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository para operações de persistência da entidade Usuario.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Optional<Usuario> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE CONCAT('%', :nome, '%')")
    List<Usuario> findByNomeContaining(@Param("nome") String nome);

    @Query("SELECT u FROM Usuario u WHERE u.createdAt >= :dataInicio")
    List<Usuario> findUsuariosCriadosApos(@Param("dataInicio") LocalDateTime dataInicio);

    @Query("SELECT u FROM Usuario u WHERE u.createdAt BETWEEN :dataInicio AND :dataFim")
    List<Usuario> findUsuariosCriadosEntre(@Param("dataInicio") LocalDateTime dataInicio, 
                                           @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT COUNT(u) FROM Usuario u")
    long countTotalUsuarios();

    @Query("SELECT u FROM Usuario u WHERE u.username = :username OR u.email = :email")
    Optional<Usuario> findByUsernameOrEmail(@Param("username") String username, 
                                           @Param("email") String email);
}


