package com.example.backapi.biblioteca.resources;

import com.example.backapi.biblioteca.domain.LinkTopico;
import com.example.backapi.biblioteca.domain.Topico;
import com.example.backapi.biblioteca.domain.TopicoDTO;
import com.example.backapi.biblioteca.services.TopicoService;
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
@RequestMapping("/topicos")
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<TopicoDTO> update(@PathVariable Integer id, @RequestBody TopicoDTO topicoDTO) throws CampoObrigatorio, ObjetoNaoEncontrado {

        topicoDTO.setId(id);
        Topico topico = modelMapper.modelMapper().map(topicoDTO, Topico.class);

        topico = topicoService.update(topico);

        topicoDTO = modelMapper.modelMapper().map(topico, TopicoDTO.class);

        return ResponseEntity.ok(topicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> findAll(){

        List<Topico> topicos = topicoService.findAll();

        List<TopicoDTO> topicosDTO = topicos.stream().map(topico -> modelMapper.modelMapper().map(topico, TopicoDTO.class)).collect(Collectors.toList());

        return ResponseEntity.ok(topicosDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws ObjetoNaoEncontrado {

        topicoService.deleteById(id);

        return ResponseEntity.ok().build();

    }

}
