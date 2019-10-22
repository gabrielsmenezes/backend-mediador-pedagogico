package com.example.backapi.calendario.resources;

import com.example.backapi.calendario.domain.Calendario;
import com.example.backapi.calendario.domain.CalendarioDTO;
import com.example.backapi.calendario.services.CalendarioService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/calendario")
public class CalendarioResource {

    @Autowired
    CalendarioService calendarioService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CalendarioDTO> save(@RequestBody CalendarioDTO calendarioDTO) throws CampoObrigatorio, LimiteDeObjetosAtingido {
        calendarioDTO = modelMapper.modelMapper().map(calendarioService.save(modelMapper.modelMapper().map(calendarioDTO, Calendario.class)), CalendarioDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(calendarioDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(calendarioDTO);

    }

}
