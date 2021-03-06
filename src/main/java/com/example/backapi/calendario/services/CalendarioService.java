package com.example.backapi.calendario.services;

import com.example.backapi.calendario.domain.Calendario;
import com.example.backapi.calendario.repositories.CalendarioRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioService {

    @Autowired
    CalendarioRepository calendarioRepository;

    public Calendario save(Calendario calendario) throws CampoObrigatorio, LimiteDeObjetosAtingido {

        verificarSeExisteLink(calendario.getLinkDoCalendario());

        List<Calendario> calendariosSalvos = calendarioRepository.findAll();

        if (!calendariosSalvos.isEmpty()){
            throw new LimiteDeObjetosAtingido("Calendario ja cadastrado anteriormente");
        }

        return calendarioRepository.save(calendario);
    }

    private void verificarSeExisteLink(String link) throws CampoObrigatorio {
        if (link == null||link.isEmpty() ){
            throw new CampoObrigatorio("Link do calendario é obrigatório");
        }
    }

    public Calendario update (Calendario calendario) throws CampoObrigatorio {
        verificarSeExisteLink(calendario.getLinkDoCalendario());
        List<Calendario> calendariosSalvos = calendarioRepository.findAll();
        if (!calendariosSalvos.isEmpty()){
            calendario.setId(calendariosSalvos.get(0).getId());
        }
        return calendarioRepository.save(calendario);
    }

    public Calendario find() throws ObjetoNaoEncontrado {
        List<Calendario> calendarios = calendarioRepository.findAll();
        if (calendarios.isEmpty()) { throw new ObjetoNaoEncontrado("Nenhum calendario cadastrado"); }
        return calendarios.get(0);
    }
}
