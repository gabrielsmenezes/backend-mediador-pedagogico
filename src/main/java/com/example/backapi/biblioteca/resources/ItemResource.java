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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id) throws ObjetoNaoEncontrado {

        itemService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ItemTopicoDTO>> listAllByTopicoId(@RequestParam Integer idDoTopico) throws ObjetoNaoEncontrado {

        List<ItemTopico> itemTopicos = itemService.findItensByTopicoId(idDoTopico);

        List<ItemTopicoDTO> itemTopicosDTO = itemTopicos.stream().map(itemTopico -> modelMapper.modelMapper().map(itemTopico, ItemTopicoDTO.class)).collect(Collectors.toList());

        itemTopicosDTO.forEach(itemTopicoDTO -> itemTopicoDTO.setIdDoTopico(idDoTopico));

        return ResponseEntity.ok(itemTopicosDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ItemTopico>> findPage(
            @RequestParam(value = "idDoTopico") Integer idDoTopico,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) throws ObjetoNaoEncontrado {

        Page<ItemTopico> pageRequest = itemService.findPage(page, linesPerPage, orderBy, direction, idDoTopico);

        return ResponseEntity.ok().body(pageRequest);
    }

}
