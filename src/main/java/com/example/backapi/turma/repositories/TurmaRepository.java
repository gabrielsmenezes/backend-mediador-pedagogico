package com.example.backapi.turma.repositories;


import com.example.backapi.turma.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}
