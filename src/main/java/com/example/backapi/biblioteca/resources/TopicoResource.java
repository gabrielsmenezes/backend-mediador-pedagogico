package com.example.backapi.biblioteca.resources;

import com.example.backapi.biblioteca.domain.Topico;
import com.example.backapi.biblioteca.domain.TopicoDTO;
import com.example.backapi.biblioteca.services.TopicoService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController("/topicos")
public class TopicoResource {

    @Autowired
    TopicoService topicoService;

    @Autowired
    ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<TopicoDTO> save(@RequestBody TopicoDTO topicoDTO) throws CampoObrigatorio {
        Topico topico = modelMapper.modelMapper().map(topicoDTO, Topico.class);

        topico = topicoService.save(topico);

        URI uri  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(topico.getId()).toUri();

        topicoDTO = modelMapper.modelMapper().map(topico, TopicoDTO.class);

        return ResponseEntity.created(uri).body(topicoDTO);

    }

}
