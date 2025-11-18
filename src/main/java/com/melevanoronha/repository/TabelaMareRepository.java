package com.melevanoronha.repository;

import com.melevanoronha.model.TabuaMare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository para operações de persistência da entidade TabelaMare.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Repository
public interface TabelaMareRepository extends JpaRepository<TabuaMare, Long> {

    @Query("SELECT t FROM TabuaMare t WHERE t.data = :data ORDER BY t.horario ASC")
    List<TabuaMare> findByData(@Param("data") LocalDate data);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TabuaMare t WHERE t.data = :data AND t.horario = :horario")
    boolean existsByDataAndHorario(@Param("data") LocalDate data, @Param("horario") String horario);

    @Query("SELECT t FROM TabuaMare t WHERE t.data = :data AND t.horario = :horario")
    TabuaMare findByDataAndHorario(@Param("data") LocalDate data, @Param("horario") String horario);

    boolean existsByData(LocalDate data);
}

