package com.example.backapi.aviso.resources;

import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.aviso.services.AvisoService;
import com.example.backapi.utils.error.StandardError;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/avisos")
public class AvisoResource{

    @Autowired
    AvisoService avisoService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Aviso> save (@RequestBody Aviso aviso) throws Exception {

        Aviso aviso_salvo = avisoService.save(aviso);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aviso_salvo.getId()).toUri();
        return ResponseEntity.created(uri).body(aviso);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Aviso> findById(@PathVariable Integer id) {

        Aviso aviso = avisoService.findById(id);

        return ResponseEntity.ok().body(aviso);
    }

    @ExceptionHandler(CampoObrigatorio.class)
    public ResponseEntity<StandardError> campoObrigatorio(CampoObrigatorio e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Campo Obrigatorio", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Titulo deve conter no maximo 100 caracteres", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Aviso>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Aviso> paginas = avisoService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(paginas);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Aviso> update (@PathVariable Integer id, @RequestBody Aviso aviso) throws CampoObrigatorio {

        aviso.setId(id);

        Aviso avisoRetornado = avisoService.update(aviso);

        return ResponseEntity.ok().body(avisoRetornado);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete (@PathVariable Integer id){

        avisoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ResponseEntity<List<Aviso>> findAll() {
        List<Aviso> avisos = avisoService.findAll();
        return ResponseEntity.ok().body(avisos);
    }

}
