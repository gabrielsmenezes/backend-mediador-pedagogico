package com.example.backapi.noticia.resources;

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

    @PostMapping
    public ResponseEntity<NoticiaDTO> save (@RequestBody NoticiaDTO noticiaDTO) throws TamanhoDeCampoExcedente, CampoObrigatorio {

        NoticiaDTO noticiaSalva = noticiaService.save(noticiaDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(noticiaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(noticiaSalva);

    }

    @GetMapping
    public ResponseEntity<Page<NoticiaDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="dataDeCriacao") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<NoticiaDTO> pagina = noticiaService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(pagina);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NoticiaDTO> update (@PathVariable Integer id, @RequestBody NoticiaDTO noticiaDTO) throws CampoObrigatorio, TamanhoDeCampoExcedente, ObjetoNaoEncontrado {

        noticiaDTO.setId(id);

        NoticiaDTO noticiaRetornada = noticiaService.update(noticiaDTO);

        return ResponseEntity.ok().body(noticiaRetornada);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){

        noticiaService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/todas")
    public ResponseEntity<List<NoticiaDTO>> findAll() {
        List<NoticiaDTO> avisos = noticiaService.findAll();
        return ResponseEntity.ok().body(avisos);
    }
}
