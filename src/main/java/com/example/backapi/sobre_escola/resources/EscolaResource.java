package com.example.backapi.sobre_escola.resources;

import com.example.backapi.sobre_escola.domain.Escola;
import com.example.backapi.sobre_escola.domain.EscolaDTO;
import com.example.backapi.sobre_escola.services.EscolaService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/escolas")
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

}
