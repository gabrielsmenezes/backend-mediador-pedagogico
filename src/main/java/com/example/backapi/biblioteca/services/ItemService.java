package com.example.backapi.biblioteca.services;

import com.example.backapi.biblioteca.domain.ItemTopico;
import com.example.backapi.biblioteca.repositories.ItemRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemTopico save(ItemTopico itemTopico) throws CampoObrigatorio {
        if (itemTopico.getTopico() == null) throw new CampoObrigatorio("Topico é obrigatorio");
        validarString(itemTopico.getNome());
        validarString(itemTopico.getLinkDoItem());

        return itemRepository.save(itemTopico);
    }

    private void validarString(String campo) throws CampoObrigatorio {
        if (campo == null || campo.isEmpty()) throw new CampoObrigatorio("Campo obrigatório necessário");
    }

    public ItemTopico update(ItemTopico itemTopico) throws CampoObrigatorio, ObjetoNaoEncontrado {
        ItemTopico itemSalvo = itemRepository.findById(itemTopico.getId()).orElseThrow(ObjetoNaoEncontrado::new);
        if (itemTopico.getTopico() == null) throw new CampoObrigatorio("Topico é obrigatorio");
        validarString(itemTopico.getNome());
        validarString(itemTopico.getLinkDoItem());
        itemTopico.setId(itemSalvo.getId());
        return itemRepository.save(itemTopico);
    }

    public void delete(Integer id) throws ObjetoNaoEncontrado {
        ItemTopico itemTopico = itemRepository.findById(id).orElseThrow(ObjetoNaoEncontrado::new);

        itemRepository.delete(itemTopico);
    }

    public List<ItemTopico> findAll() {
        return itemRepository.findAll();
    }
}
