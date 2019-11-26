package com.example.backapi.aula_invertida.repositories;

import com.example.backapi.aula_invertida.domain.material.Material;
import com.example.backapi.aula_invertida.domain.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    ArrayList<Material> findByTurma(Turma turma);
}
