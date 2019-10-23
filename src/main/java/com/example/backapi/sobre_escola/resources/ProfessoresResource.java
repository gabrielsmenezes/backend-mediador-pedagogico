package com.example.backapi.sobre_escola.resources;

import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.sobre_escola.domain.Professor;
import com.example.backapi.sobre_escola.domain.ProfessorDTO;
import com.example.backapi.sobre_escola.services.ProfessorService;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/professores")
public class ProfessoresResource {

    @Autowired
    ProfessorService professorService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ProfessorDTO> save(@RequestBody ProfessorDTO professorDTO) {
        Professor professor = modelMapper.modelMapper().map(professorDTO, Professor.class);

        professorDTO = modelMapper.modelMapper().map(professorService.save(professor), ProfessorDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(professorDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(professorDTO);
    }

    @PutMapping
    public ResponseEntity<ProfessorDTO> update (@RequestBody ProfessorDTO professorDTO) {
        Professor professorSalva = professorService.update(modelMapper.modelMapper().map(professorDTO, Professor.class));

        ProfessorDTO dto = modelMapper.modelMapper().map(professorSalva, ProfessorDTO.class);

        return ResponseEntity.ok(dto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){

        professorService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        List<Professor> professors = professorService.findAll();
        List<ProfessorDTO> professorsDTO = professors.stream().map(professor -> modelMapper.modelMapper().map(professor, ProfessorDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(professorsDTO);
    }


}
