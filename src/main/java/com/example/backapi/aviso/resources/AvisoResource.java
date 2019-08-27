package com.example.backapi.aviso.resources;

import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.aviso.services.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avisos")
public class AvisoResource {

    @Autowired
    AvisoService avisoService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Aviso> save (@RequestBody Aviso aviso){

        avisoService.save(aviso);

        return ResponseEntity.ok().body(aviso);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Aviso> findById(@PathVariable Integer id) {

        Aviso aviso = avisoService.findById(id);

        return ResponseEntity.ok().body(aviso);
    }


}
