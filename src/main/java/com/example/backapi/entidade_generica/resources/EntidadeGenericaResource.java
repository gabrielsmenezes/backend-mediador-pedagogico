package com.example.backapi.entidade_generica.resources;

import com.example.backapi.entidade_generica.domain.EntidadeGenerica;
import com.example.backapi.entidade_generica.services.EntidadeGenericaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entidade-generica")
public class EntidadeGenericaResource {

    @Autowired
    EntidadeGenericaService entidadeGenericaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EntidadeGenerica> save (@RequestBody EntidadeGenerica entidadeGenerica){

        entidadeGenericaService.save(entidadeGenerica);

        return ResponseEntity.ok().body(entidadeGenerica);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EntidadeGenerica> findById(@PathVariable Integer id) {

        EntidadeGenerica entidadeGenerica = entidadeGenericaService.findById(id);

        return ResponseEntity.ok().body(entidadeGenerica);
    }


}
