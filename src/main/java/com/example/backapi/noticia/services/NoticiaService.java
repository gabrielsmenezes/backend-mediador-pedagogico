package com.example.backapi.noticia.services;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.noticia.repositories.NoticiaRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.example.backapi.utils.exceptions.TamanhoDeCampoExcedente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {


    @Autowired
    NoticiaRepository noticiaRepository;

    public NoticiaDTO save(NoticiaDTO noticiaDTO) throws TamanhoDeCampoExcedente, CampoObrigatorio {
        validarExistenciaTitulo(noticiaDTO);
        validarExistenciaLink(noticiaDTO);
        validarExistenciaNotificavel(noticiaDTO);
        validarTamanhoTitulo(noticiaDTO);

        Noticia noticia = dtoToNoticia(noticiaDTO);

        noticia.setDataDeCriacao(new java.util.Date());
        noticia = noticiaRepository.save(noticia);

        return noticiaToDTO(noticia);
    }

    private void validarExistenciaNotificavel(NoticiaDTO noticiaDTO) throws CampoObrigatorio {
        if(noticiaDTO.isNotificavel() == null){
            throw new CampoObrigatorio("O notificavel é obrigatório");
        }
    }

    private void validarExistenciaLink(NoticiaDTO noticiaDTO) throws CampoObrigatorio {
        if(noticiaDTO.getLinks() == null || noticiaDTO.getLinks().isEmpty()){
            throw new CampoObrigatorio("O link é obrigatório");
        }
    }

    private void validarExistenciaTitulo(NoticiaDTO noticiaDTO) throws CampoObrigatorio {
        if (noticiaDTO.getTitulo() == null || noticiaDTO.getTitulo().isEmpty()){
            throw new CampoObrigatorio("O titulo é obrigatório");
        }
    }

    private NoticiaDTO noticiaToDTO(Noticia noticia) {
        NoticiaDTO noticiaDTO = new NoticiaDTO();
        noticiaDTO.setId(noticia.getId());
        noticiaDTO.setTitulo(noticia.getTitulo());
        noticiaDTO.setDescricao(noticia.getDescricao());
        noticiaDTO.setLinks(noticia.getLinks());
        noticiaDTO.setDataDeCriacao(noticia.getDataDeCriacao());
        noticiaDTO.setNotificavel(noticia.isNotificavel());
        return noticiaDTO;
    }

    private Noticia dtoToNoticia(NoticiaDTO noticiaDTO) {
        Noticia noticia = new Noticia();
        noticia.setId(noticiaDTO.getId());
        noticia.setTitulo(noticiaDTO.getTitulo());
        noticia.setDescricao(noticiaDTO.getDescricao());
        noticia.setLinks(noticiaDTO.getLinks());
        noticia.setDataDeCriacao(noticiaDTO.getDataDeCriacao());
        noticia.setNotificavel(noticiaDTO.isNotificavel());
        return noticia;
    }

    private void validarTamanhoTitulo(NoticiaDTO noticiaDTO) throws TamanhoDeCampoExcedente {
        if (noticiaDTO.getTitulo().length() > 300) {
            throw new TamanhoDeCampoExcedente("O tamanho do campo titulo é de no máximo 300 caracteres");
        }
    }

    public PageImpl<NoticiaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        List<NoticiaDTO> noticias = findAll();
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return new PageImpl<>(noticias, pageRequest, noticias.size());
    }

    public NoticiaDTO update(NoticiaDTO noticiaDTO) throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado {
        validarExistenciaTitulo(noticiaDTO);
        validarExistenciaLink(noticiaDTO);
        validarExistenciaNotificavel(noticiaDTO);
        validarTamanhoTitulo(noticiaDTO);

        findById(noticiaDTO.getId());
        Noticia noticia = dtoToNoticia(noticiaDTO);
        noticia = noticiaRepository.save(noticia);
        return noticiaToDTO(noticia);
    }

    public NoticiaDTO findById(Integer id) throws ObjetoNaoEncontrado {
        return noticiaToDTO(noticiaRepository.findById(id).orElseThrow(ObjetoNaoEncontrado::new));
    }

    public void delete(Integer id) {
        noticiaRepository.deleteById(id);
    }

    public List<NoticiaDTO> findAll() {
        List<Noticia> noticias = noticiaRepository.findAll();
        ArrayList<NoticiaDTO> noticiasDTO = new ArrayList<>();
        for (Noticia noticia: noticias) {
            noticiasDTO.add(noticiaToDTO(noticia));
        }
        return noticiasDTO;
    }
}