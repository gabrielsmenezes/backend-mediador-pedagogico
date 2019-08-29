package com.example.backapi.aula_invertida.repositories;

import com.example.backapi.aula_invertida.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
