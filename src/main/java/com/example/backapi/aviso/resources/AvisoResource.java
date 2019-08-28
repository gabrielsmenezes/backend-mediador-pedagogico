package com.example.backapi.aviso.resources;

import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.aviso.resources.error.StandardError;
import com.example.backapi.aviso.services.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/avisos")
public class AvisoResource {

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

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<StandardError> transactionSystemException(TransactionSystemException e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Titulo deve conter no maximo 100 caracteres", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }



}
