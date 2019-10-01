package com.example.backapi.noticia.resources;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.noticia.services.NoticiaService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.example.backapi.utils.exceptions.TamanhoDeCampoExcedente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<NoticiaDTO> update (@PathVariable Integer id, @RequestBody NoticiaDTO noticiaDTO) throws CampoObrigatorio, TamanhoDeCampoExcedente, ObjetoNaoEncontrado {

        noticiaDTO.setId(id);

        NoticiaDTO noticiaRetornada = noticiaService.update(noticiaDTO);

        return ResponseEntity.ok().body(noticiaRetornada);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete (@PathVariable Integer id){

        noticiaService.delete(id);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/todas", method = RequestMethod.GET)
    public ResponseEntity<List<NoticiaDTO>> findAll() {
        List<NoticiaDTO> avisos = noticiaService.findAll();
        return ResponseEntity.ok().body(avisos);
    }
}
