package com.example.backapi.resources;

import com.example.backapi.domain.EntidadeGenerica;
import com.example.backapi.services.EntidadeGenericaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entidade-generica")
public class EntidadeGenericaResource {

    @Autowired
    EntidadeGenericaService entidadeGenericaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EntidadeGenerica> findById(@PathVariable Integer id) {

        EntidadeGenerica entidadeGenerica = entidadeGenericaService.findById(id);

        return ResponseEntity.ok().body(entidadeGenerica);

    }

}
