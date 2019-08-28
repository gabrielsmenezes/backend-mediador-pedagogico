package com.example.backapi.aviso.repositories;

import com.example.backapi.aviso.domain.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Integer> {
}
