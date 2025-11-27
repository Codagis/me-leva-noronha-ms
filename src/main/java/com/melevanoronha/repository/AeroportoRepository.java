package com.melevanoronha.repository;

import com.melevanoronha.model.Aeroporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório responsável por operações de banco de dados relacionadas a aeroportos.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Repository
public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {
    Optional<Aeroporto> findByCodigoIATA(String codigoIATA);
    boolean existsByCodigoIATA(String codigoIATA);
}

