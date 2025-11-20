package com.melevanoronha.repository;

import com.melevanoronha.model.PontoInteresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoInteresseRepository extends JpaRepository<PontoInteresse, Long> {
}
