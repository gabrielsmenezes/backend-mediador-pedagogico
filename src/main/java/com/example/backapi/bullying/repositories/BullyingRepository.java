package com.example.backapi.bullying.repositories;

import com.example.backapi.bullying.domain.Bullying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BullyingRepository extends JpaRepository<Bullying, Integer> {
}
