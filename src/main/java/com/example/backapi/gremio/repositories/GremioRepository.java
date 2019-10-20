package com.example.backapi.gremio.repositories;

import com.example.backapi.gremio.domain.Gremio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GremioRepository extends JpaRepository<Gremio, Integer> {
}
