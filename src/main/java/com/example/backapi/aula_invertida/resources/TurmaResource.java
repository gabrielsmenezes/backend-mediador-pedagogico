package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.example.backapi.aula_invertida.domain.turma.TurmaDTO;
import com.example.backapi.aula_invertida.services.TurmaService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {

    @Autowired
    TurmaService turmaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<TurmaDTO> save (@RequestBody TurmaDTO turmaDTO) throws CampoObrigatorio {
        Turma turma = modelMapper.modelMapper().map(turmaDTO, Turma.class);

        turma = turmaService.save(turma);

        TurmaDTO turmaSalva = modelMapper.modelMapper().map(turma, TurmaDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(turmaSalva.getId()).toUri();

        return ResponseEntity.created(uri).body(turmaSalva);
    }

    @GetMapping(value = "/todas")
    public ResponseEntity<List<TurmaDTO>> findAll() {
        List<Turma> turmas = turmaService.findAll();

        List<TurmaDTO> turmasDTO = turmas.stream().map(turma -> modelMapper.modelMapper().map(turma, TurmaDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(turmasDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id) throws ObjetoNaoEncontrado {

        turmaService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TurmaDTO> update (@PathVariable Integer id, @RequestBody TurmaDTO turmaDTO) throws CampoObrigatorio, ObjetoNaoEncontrado {

        turmaDTO.setId(id);

        TurmaDTO turmaRetornada = modelMapper.modelMapper().map(
                turmaService.update(modelMapper.modelMapper().
                map(turmaDTO, Turma.class)), TurmaDTO.class);

        return ResponseEntity.ok().body(turmaRetornada);
    }

    @GetMapping(value = "/{idDaTurma}/alunos")
    public List<Aluno> findAlunoByIdTurma(@PathVariable Integer idDaTurma) throws ObjetoNaoEncontrado {
        return turmaService.findAlunoByTurmaId(idDaTurma);
    }
}
