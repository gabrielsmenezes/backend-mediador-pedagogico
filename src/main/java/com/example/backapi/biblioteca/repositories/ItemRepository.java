package com.example.backapi.biblioteca.repositories;

import com.example.backapi.biblioteca.domain.ItemTopico;
import com.example.backapi.biblioteca.domain.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemTopico, Integer> {

List<ItemTopico> findItemTopicosByTopicoIs(Topico topico);
Page<ItemTopico> findItemTopicosByTopicoIs(Topico topico, Pageable pageable);


}
