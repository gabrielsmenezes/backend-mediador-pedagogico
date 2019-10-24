package com.example.backapi.sobre_escola.resources;

import com.example.backapi.sobre_escola.domain.Escola;
import com.example.backapi.sobre_escola.domain.EscolaDTO;
import com.example.backapi.sobre_escola.services.EscolaService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/escola")
public class EscolaResource {

    @Autowired
    EscolaService escolaService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<EscolaDTO> save( @RequestBody EscolaDTO escolaDTO) throws CampoObrigatorio, LimiteDeObjetosAtingido {
        Escola escola = modelMapper.modelMapper().map(escolaDTO, Escola.class);

        escolaDTO = modelMapper.modelMapper().map(escolaService.save(escola), EscolaDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(escolaDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(escolaDTO);
    }

    @PutMapping
    public ResponseEntity<EscolaDTO> update (@RequestBody EscolaDTO escolaDTO) throws CampoObrigatorio {
        Escola escolaSalva = escolaService.update(modelMapper.modelMapper().map(escolaDTO, Escola.class));

        EscolaDTO dto = modelMapper.modelMapper().map(escolaSalva, EscolaDTO.class);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public  ResponseEntity<EscolaDTO> find() throws ObjetoNaoEncontrado {
        Escola escolaSalva = escolaService.find();
        EscolaDTO escolaDTO = modelMapper.modelMapper().map(escolaSalva, EscolaDTO.class);
        return ResponseEntity.ok(escolaDTO);
    }
}
