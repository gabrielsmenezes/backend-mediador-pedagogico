package com.example.backapi.entidade_generica.repositories;

import com.example.backapi.entidade_generica.domain.EntidadeGenerica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeGenericaRepository extends JpaRepository<EntidadeGenerica, Integer> {
}
