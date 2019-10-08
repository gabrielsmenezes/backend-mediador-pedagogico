package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.services.AlunoService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Aluno> save (@RequestParam String chaveDeAcesso, String nome) throws ObjetoNaoEncontrado, CampoObrigatorio {
        Aluno aluno = new Aluno();
        aluno.setChaveDeAcesso(Encode.forHtml(chaveDeAcesso));
        aluno.setNome(Encode.forHtml(nome));

        Aluno alunoSalvo = alunoService.save(aluno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(alunoSalvo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id) throws ObjetoNaoEncontrado {

        alunoService.delete(id);

        return ResponseEntity.ok().build();
    }

}
