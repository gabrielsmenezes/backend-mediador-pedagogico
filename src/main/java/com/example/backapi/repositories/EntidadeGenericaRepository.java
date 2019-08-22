package com.example.backapi.repositories;

import com.example.backapi.domain.EntidadeGenerica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadeGenericaRepository extends JpaRepository<EntidadeGenerica, Integer> {
}
