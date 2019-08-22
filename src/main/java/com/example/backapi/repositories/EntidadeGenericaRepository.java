package com.example.backapi.repositories;

import com.example.backapi.domain.EntidadeGenerica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeGenericaRepository extends JpaRepository<EntidadeGenerica, Integer> {
}
