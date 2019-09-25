package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.material.Material;
import com.example.backapi.aula_invertida.domain.material.MaterialDTO;
import com.example.backapi.aula_invertida.services.MaterialService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/aulas")
public class MaterialResource {

    @Autowired
    MaterialService materialService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MaterialDTO> save (@RequestBody MaterialDTO materialDTO) throws CampoObrigatorio {

        MaterialDTO material_salvo = materialService.save(materialDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(material_salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(material_salvo);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Material>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Material> paginas = materialService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(paginas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MaterialDTO> update (@PathVariable Integer id, @RequestBody MaterialDTO materialDTO) throws CampoObrigatorio {

        materialDTO.setId(id);

        MaterialDTO materialDTO_Retorado = materialService.update(materialDTO);

        return ResponseEntity.ok().body(materialDTO_Retorado);
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ResponseEntity<List<MaterialDTO>> findAll(@RequestParam Integer idDaTurma) {
        List<MaterialDTO> materiais = materialService.findAllById(idDaTurma);
        return ResponseEntity.ok().body(materiais);
    }

}
