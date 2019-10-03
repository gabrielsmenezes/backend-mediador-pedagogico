package com.example.backapi.aviso.resources;

import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.aviso.domain.AvisoDTO;
import com.example.backapi.aviso.services.AvisoService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.mapper.ModelMapper;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AvisoDTO> save (@RequestBody AvisoDTO avisoDTO) throws Exception {

        AvisoDTO aviso_salvo = modelMapper.modelMapper().map(avisoService.save(modelMapper.modelMapper().map(avisoDTO, Aviso.class)), AvisoDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aviso_salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(avisoDTO);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AvisoDTO> findById(@PathVariable Integer id){

        AvisoDTO avisoDTO = modelMapper.modelMapper().map(avisoService.findById(id), AvisoDTO.class);

        return ResponseEntity.ok().body(avisoDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<AvisoDTO>> findPage(@RequestParam(value="page", defaultValue="0") Integer page, @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, @RequestParam(value="orderBy", defaultValue="dataDeCriacao") String orderBy, @RequestParam(value="direction", defaultValue="DESC") String direction) {

        Page<Aviso> pagina = avisoService.findPage(page, linesPerPage, orderBy, direction);

        List<AvisoDTO> DTOs = pagina.stream().map(aviso -> modelMapper.modelMapper().map(aviso, AvisoDTO.class))
                .collect(Collectors.toList());

        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);


        Page<AvisoDTO> paginaDTO = new PageImpl<AvisoDTO>(DTOs, pageRequest,DTOs.size());

        return ResponseEntity.ok().body(paginaDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AvisoDTO> update (@PathVariable Integer id, @RequestBody AvisoDTO avisoDTO) throws CampoObrigatorio, IOException, FirebaseMessagingException {

        avisoDTO.setId(id);

        AvisoDTO avisoRetornado = modelMapper.modelMapper().map(avisoService.update(modelMapper.modelMapper().map(avisoDTO, Aviso.class)), AvisoDTO.class);

        return ResponseEntity.ok().body(avisoRetornado);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete (@PathVariable Integer id){

        avisoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ResponseEntity<List<AvisoDTO>> findAll() {
        List<Aviso> avisos = avisoService.findAll();

        List<AvisoDTO> avisosDTO = avisos.stream().map(aviso -> modelMapper.modelMapper().map(aviso, AvisoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(avisosDTO);
    }
}
