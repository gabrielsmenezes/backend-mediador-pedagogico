package com.example.backapi.bullying.resources;

import com.example.backapi.bullying.domain.Bullying;
import com.example.backapi.bullying.domain.BullyingDTO;
import com.example.backapi.bullying.services.BullyingService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/bullying")
public class BullyingResource {
    @Autowired
    BullyingService bullyingService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<BullyingDTO> save(@RequestBody BullyingDTO bullyingDTO) throws CampoObrigatorio, LimiteDeObjetosAtingido {
        Bullying bullyingSalvo = bullyingService.save(modelMapper.modelMapper().map(bullyingDTO, Bullying.class));
        bullyingDTO = modelMapper.modelMapper().map(bullyingSalvo, BullyingDTO.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bullyingDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(bullyingDTO);
    }

    @PutMapping
    public ResponseEntity<BullyingDTO> update(@RequestBody BullyingDTO bullyingDTO) throws CampoObrigatorio {
        Bullying bullyingSalvo = bullyingService.update(modelMapper.modelMapper().map(bullyingDTO, Bullying.class));

        BullyingDTO dto = modelMapper.modelMapper().map(bullyingSalvo, BullyingDTO.class);

        return ResponseEntity.ok(dto);
    }

}
