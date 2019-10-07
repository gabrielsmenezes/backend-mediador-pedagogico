package com.example.backapi.aviso.resources;

import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.aviso.domain.AvisoDTO;
import com.example.backapi.aviso.services.AvisoService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
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
@RequestMapping("/avisos")
public class AvisoResource{

    @Autowired
    AvisoService avisoService;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<AvisoDTO> save (@RequestBody AvisoDTO avisoDTO) throws CampoObrigatorio {
        Aviso aviso = modelMapper.modelMapper().map(avisoDTO, Aviso.class);
        avisoService.save(aviso);
        AvisoDTO avisoDTOSalvo = modelMapper.modelMapper().map(aviso, AvisoDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(avisoDTOSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(avisoDTOSalvo);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AvisoDTO> findById(@PathVariable Integer id){
        Aviso aviso = avisoService.findById(id);
        AvisoDTO avisoDTO = modelMapper.modelMapper().map(aviso, AvisoDTO.class);

        return ResponseEntity.ok().body(avisoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<AvisoDTO>> findPage(@RequestParam(value="page", defaultValue="0") Integer page, @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, @RequestParam(value="orderBy", defaultValue="dataDeCriacao") String orderBy, @RequestParam(value="direction", defaultValue="DESC") String direction) {

        Page<Aviso> pagina = avisoService.findPage(page, linesPerPage, orderBy, direction);

        List<AvisoDTO> dtos = pagina.stream().map(aviso -> modelMapper.modelMapper().map(aviso, AvisoDTO.class))
                .collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);


        Page<AvisoDTO> paginaDTO = new PageImpl<>(dtos, pageRequest, dtos.size());

        return ResponseEntity.ok().body(paginaDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AvisoDTO> update (@PathVariable Integer id, @RequestBody AvisoDTO avisoDTO) throws CampoObrigatorio {

        avisoDTO.setId(id);

        AvisoDTO avisoRetornado = modelMapper.modelMapper().map(avisoService.update(modelMapper.modelMapper().map(avisoDTO, Aviso.class)), AvisoDTO.class);

        return ResponseEntity.ok().body(avisoRetornado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){

        avisoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<AvisoDTO>> findAll() {
        List<Aviso> avisos = avisoService.findAll();

        List<AvisoDTO> avisosDTO = avisos.stream().map(aviso -> modelMapper.modelMapper().map(aviso, AvisoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(avisosDTO);
    }
}
