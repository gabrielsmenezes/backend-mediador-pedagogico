package com.example.backapi.aula_invertida.repositories;


import com.example.backapi.aula_invertida.domain.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}
