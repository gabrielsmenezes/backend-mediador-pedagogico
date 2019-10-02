package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.example.backapi.aula_invertida.services.TurmaService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/todas", method = RequestMethod.GET)
    public ResponseEntity<List<Turma>> findAll() {
        List<Turma> turmas = turmaService.findAll();
        return ResponseEntity.ok().body(turmas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete (@PathVariable Integer id) throws ObjetoNaoEncontrado {

        turmaService.delete(id);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Turma> update (@PathVariable Integer id, @RequestBody Turma turma) throws CampoObrigatorio, ObjetoNaoEncontrado {

        turma.setId(id);

        Turma turma_retornada = turmaService.update(turma);

        return ResponseEntity.ok().body(turma_retornada);
    }

    @RequestMapping(value = "/{idDaTurma}/alunos", method = RequestMethod.GET)
    public List<Aluno> findAlunoByIdTurma(@PathVariable Integer idDaTurma) throws ObjetoNaoEncontrado {
        return turmaService.findAlunoByTurmaId(idDaTurma);
    }
}
