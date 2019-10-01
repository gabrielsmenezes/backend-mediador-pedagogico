package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.services.AlunoService;
import com.example.backapi.utils.error.StandardError;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Aluno> save (@RequestParam String chaveDeAcesso, String nome) throws Exception {
        Aluno aluno = new Aluno();
        aluno.setChaveDeAcesso(Encode.forHtml(chaveDeAcesso));
        aluno.setNome(Encode.forHtml(nome));

        Aluno aluno_salvo = alunoService.save(aluno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno_salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(aluno_salvo);
    }

    @ExceptionHandler(ObjetoNaoEncontrado.class)
    public ResponseEntity<StandardError> turmaNaoEncontrada(ObjetoNaoEncontrado e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Nenhuma turma cadastrada com essa chave", e.getMessage(), Encode.forHtml(request.getRequestURI()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(CampoObrigatorio.class)
    public ResponseEntity<StandardError> campoObrigatorio(CampoObrigatorio e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Campo obrigatório", e.getMessage(), Encode.forHtml(request.getRequestURI()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
