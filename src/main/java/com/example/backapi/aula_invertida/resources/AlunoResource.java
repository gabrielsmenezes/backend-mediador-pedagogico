package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.Aluno;
import com.example.backapi.aula_invertida.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Aluno> save (@RequestBody Aluno aluno) throws Exception {

        Aluno aluno_salvo = alunoService.save(aluno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno_salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(aluno_salvo);
    }
}
