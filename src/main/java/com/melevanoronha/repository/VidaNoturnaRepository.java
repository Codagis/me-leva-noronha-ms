package com.melevanoronha.repository;

import com.melevanoronha.model.VidaNoturna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA responsável por operações CRUD da entidade VidaNoturna, permitindo
 * gerenciar os registros de vida noturna persistidos no banco de dados.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Repository
public interface VidaNoturnaRepository extends JpaRepository<VidaNoturna, Long> {
}


