package com.melevanoronha.repository;

import com.melevanoronha.model.TaxiPreco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxiPrecoRepository extends JpaRepository<TaxiPreco, Long> {
    
    Optional<TaxiPreco> findByOrigemIgnoreCaseAndDestinoIgnoreCase(String origem, String destino);
    
    boolean existsByOrigemIgnoreCaseAndDestinoIgnoreCase(String origem, String destino);
}

