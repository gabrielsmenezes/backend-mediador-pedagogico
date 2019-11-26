package com.example.backapi.gremio.resources;

import com.example.backapi.gremio.domain.Gremio;
import com.example.backapi.gremio.domain.GremioDTO;
import com.example.backapi.gremio.services.GremioService;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/gremio")
public class GremioResource {

    @Autowired
    GremioService gremioService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<GremioDTO> save (@RequestBody GremioDTO gremioDTO) throws LimiteDeObjetosAtingido {
        Gremio gremio = modelMapper.modelMapper().map(gremioDTO, Gremio.class);

        gremioDTO = modelMapper.modelMapper().map(gremioService.save(gremio), GremioDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(gremioDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(gremioDTO);
    }

    @PutMapping
    public ResponseEntity<GremioDTO> update (@RequestBody GremioDTO gremioDTO){
        Gremio gremioSalvo = gremioService.update(modelMapper.modelMapper().map(gremioDTO, Gremio.class));

        GremioDTO dto = modelMapper.modelMapper().map(gremioSalvo, GremioDTO.class);

        return ResponseEntity.ok(dto);


    }

    @GetMapping
    public ResponseEntity<GremioDTO> find() throws ObjetoNaoEncontrado {
        Gremio gremio = gremioService.find();

        GremioDTO gremioDTO = modelMapper.modelMapper().map(gremio, GremioDTO.class);

        return ResponseEntity.ok(gremioDTO);
    }
}
