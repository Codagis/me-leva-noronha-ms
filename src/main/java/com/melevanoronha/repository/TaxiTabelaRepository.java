package com.melevanoronha.repository;

import com.melevanoronha.model.TaxiTabela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxiTabelaRepository extends JpaRepository<TaxiTabela, Long> {
    
    Optional<TaxiTabela> findByNomeIgnoreCase(String nome);
    
    boolean existsByNomeIgnoreCase(String nome);
}

