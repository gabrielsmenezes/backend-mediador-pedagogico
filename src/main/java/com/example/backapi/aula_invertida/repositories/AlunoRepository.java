package com.example.backapi.aula_invertida.repositories;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
