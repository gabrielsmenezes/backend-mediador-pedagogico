package com.example.backapi.noticia.resources;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.noticia.services.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Noticia>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="dataDeCriacao") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<Noticia> pagina = noticiaService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(pagina);
    }
}
