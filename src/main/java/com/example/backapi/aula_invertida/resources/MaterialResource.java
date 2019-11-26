package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.material.MaterialDTO;
import com.example.backapi.aula_invertida.services.MaterialService;
import com.example.backapi.utils.exceptions.AcessoNegado;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/aulas")
public class MaterialResource {

    @Autowired
    MaterialService materialService;

    @PostMapping
    public ResponseEntity<MaterialDTO> save (@RequestBody MaterialDTO materialDTO) throws CampoObrigatorio, ObjetoNaoEncontrado {

        MaterialDTO materialSalvo = materialService.save(materialDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(materialSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(materialSalvo);
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Page<MaterialDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction,
            @RequestParam(value = "chaveDeAcesso") String chaveDeAcesso,
            @RequestParam(value = "idDoAluno") Integer idDoAluno) throws ObjetoNaoEncontrado, AcessoNegado {

        materialService.validarAlunoEmTurma(idDoAluno, chaveDeAcesso);

        Page<MaterialDTO> paginas = materialService.findPage(page, linesPerPage, orderBy, direction, chaveDeAcesso);
        return ResponseEntity.ok().body(paginas);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MaterialDTO> update (@PathVariable Integer id, @RequestBody MaterialDTO materialDTO) throws CampoObrigatorio, ObjetoNaoEncontrado {

        materialDTO.setId(Integer.valueOf(Encode.forHtml(String.valueOf(id))));

        MaterialDTO materialDTORetorado = materialService.update(materialDTO);

        return ResponseEntity.ok().body(materialDTORetorado);
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<MaterialDTO>> findAll(@RequestParam Integer idDaTurma) throws ObjetoNaoEncontrado {
        List<MaterialDTO> materiais = materialService.findAllById(Integer.valueOf(org.owasp.encoder.Encode.forHtml(String.valueOf(idDaTurma))));
        return ResponseEntity.ok().body(materiais);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id) throws ObjetoNaoEncontrado {

        materialService.delete(id);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(AcessoNegado.class)
    public ResponseEntity<AcessoNegado> acessoNegado(AcessoNegado acessoNegado){

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(acessoNegado);

    }

}
