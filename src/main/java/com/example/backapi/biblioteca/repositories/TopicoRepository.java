package com.example.backapi.biblioteca.repositories;

import com.example.backapi.biblioteca.domain.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {
}
