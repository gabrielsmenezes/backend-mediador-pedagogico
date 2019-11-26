package com.example.backapi.sobre_escola.repositories;

import com.example.backapi.sobre_escola.domain.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscolaRepository extends JpaRepository<Escola, Integer> {
}
