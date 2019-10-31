package com.example.backapi.biblioteca.resources;

import com.example.backapi.biblioteca.domain.ItemTopico;
import com.example.backapi.biblioteca.domain.ItemTopicoDTO;
import com.example.backapi.biblioteca.domain.Topico;
import com.example.backapi.biblioteca.services.ItemService;
import com.example.backapi.biblioteca.services.TopicoService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/itens")
public class ItemResource {

    @Autowired
    ItemService itemService;

    @Autowired
    TopicoService topicoService;

    @Autowired
    ModelMapper modelMapper;
    
    @PostMapping
    public ResponseEntity<ItemTopicoDTO> save(@RequestBody ItemTopicoDTO itemTopicoDTO) throws ObjetoNaoEncontrado, CampoObrigatorio {
        ItemTopico link = modelMapper.modelMapper().map(itemTopicoDTO, ItemTopico.class);

        Topico topico = topicoService.findById(itemTopicoDTO.getIdDoTopico());

        link.setTopico(topico);

        link = itemService.save(link);

        itemTopicoDTO = modelMapper.modelMapper().map(link, ItemTopicoDTO.class);

        itemTopicoDTO.setIdDoTopico(link.getTopico().getId());

        URI uri  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(link.getId()).toUri();

        return ResponseEntity.created(uri).body(itemTopicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemTopicoDTO> update(@RequestBody ItemTopicoDTO itemTopicoDTO, @PathVariable Integer id) throws ObjetoNaoEncontrado, CampoObrigatorio {
        itemTopicoDTO.setId(id);
        ItemTopico itemTopico = modelMapper.modelMapper().map(itemTopicoDTO, ItemTopico.class);
        Topico topico = topicoService.findById(itemTopicoDTO.getIdDoTopico());
        itemTopico.setTopico(topico);
        itemTopico = itemService.update(itemTopico);
        itemTopicoDTO = modelMapper.modelMapper().map(itemTopico, ItemTopicoDTO.class);
        itemTopicoDTO.setIdDoTopico(topico.getId());
        return ResponseEntity.ok(itemTopicoDTO);

    }
}
