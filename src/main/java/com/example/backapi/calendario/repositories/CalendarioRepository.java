package com.example.backapi.calendario.repositories;

import com.example.backapi.calendario.domain.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Integer> {
}
