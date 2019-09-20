package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.Turma;
import com.example.backapi.aula_invertida.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {

    @Autowired
    TurmaService turmaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Turma> save (@RequestBody Turma turma) throws Exception {

        Turma turma_salva = turmaService.save(turma);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(turma_salva.getId()).toUri();
        return ResponseEntity.created(uri).body(turma_salva);
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ResponseEntity<List<Turma>> findAll() {
        List<Turma> turmas = turmaService.findAll();
        return ResponseEntity.ok().body(turmas);
    }

}
