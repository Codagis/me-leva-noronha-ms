package com.melevanoronha.repository;

import com.melevanoronha.enumerator.CategoriaRestaurante;
import com.melevanoronha.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    
    List<Restaurante> findByCategoria(CategoriaRestaurante categoria);
}

