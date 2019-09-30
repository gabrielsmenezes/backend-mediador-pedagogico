package com.example.backapi.noticia.resources;

import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.noticia.services.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/noticias")
public class NoticiaResource {

    @Autowired
    NoticiaService noticiaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<NoticiaDTO> save (@RequestBody NoticiaDTO noticiaDTO) throws Exception {

        NoticiaDTO noticia_salva = noticiaService.save(noticiaDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(noticia_salva.getId()).toUri();
        return ResponseEntity.created(uri).body(noticia_salva);

    }

}
