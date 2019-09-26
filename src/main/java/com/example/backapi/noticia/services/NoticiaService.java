package com.example.backapi.noticia.services;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.noticia.repositories.NoticiaRepository;
import com.example.backapi.utils.exceptions.TamanhoDeCampoExcedente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaService {

    @Autowired
    NoticiaRepository noticiaRepository;

    public NoticiaDTO save(NoticiaDTO noticiaDTO) throws TamanhoDeCampoExcedente {

        validarTamanhoTitulo(noticiaDTO);
        validarTamanhoDescricao(noticiaDTO);

        Noticia noticia = DTOToNoticia(noticiaDTO);

        noticia.setDataDeCriacao(new java.util.Date());
        noticia = noticiaRepository.save(noticia);

        NoticiaDTO noticiaDTO_retornada = noticiaToDTO(noticia);

        return noticiaDTO_retornada;
    }

    private NoticiaDTO noticiaToDTO(Noticia noticia) {
        NoticiaDTO noticiaDTO = new NoticiaDTO();
        noticiaDTO.setId(noticia.getId());
        noticiaDTO.setTitulo(noticia.getTitulo());
        noticiaDTO.setDescricao(noticia.getDescricao());
        noticiaDTO.setLink(noticia.getLink());
        noticiaDTO.setDataDeCriacao(noticia.getDataDeCriacao());
        noticiaDTO.setNotificavel(noticia.isNotificavel());
        return noticiaDTO;
    }

    private Noticia DTOToNoticia(NoticiaDTO noticiaDTO) {
        Noticia noticia = new Noticia();
        noticia.setId(noticiaDTO.getId());
        noticia.setTitulo(noticiaDTO.getTitulo());
        noticia.setDescricao(noticiaDTO.getDescricao());
        noticia.setLink(noticiaDTO.getLink());
        noticia.setDataDeCriacao(noticiaDTO.getDataDeCriacao());
        noticia.setNotificavel(noticiaDTO.isNotificavel());
        return noticia;
    }

    private void validarTamanhoDescricao(NoticiaDTO noticiaDTO) throws TamanhoDeCampoExcedente {
        if (noticiaDTO.getDescricao().length() > 100) {
            throw new TamanhoDeCampoExcedente("O tamanho do campo descricao é de no máximo 100 caracteres");
        }
    }

    private void validarTamanhoTitulo(NoticiaDTO noticiaDTO) throws TamanhoDeCampoExcedente {
        if (noticiaDTO.getTitulo().length() > 50) {
            throw new TamanhoDeCampoExcedente("O tamanho do campo titulo é de no máximo 50 caracteres");
        }
    }

}