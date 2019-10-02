package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.material.Material;
import com.example.backapi.aula_invertida.domain.material.MaterialDTO;
import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.example.backapi.aula_invertida.repositories.MaterialRepository;
import com.example.backapi.notificacao.model.PushNotificationRequest;
import com.example.backapi.notificacao.service.PushNotificationService;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    TurmaService turmaService;

    @Autowired
    PushNotificationService pushNotificationService;

    public MaterialDTO save(MaterialDTO materialDTO) throws CampoObrigatorio, ObjetoNaoEncontrado, IOException, FirebaseMessagingException {
        validarTurma(materialDTO);

        validarTitulo(materialDTO);

        validarDescricaoImagemLinks(materialDTO);

        Date date = new java.util.Date();
        materialDTO.setDataDeCriacao(date);

        Material material = DTOToMaterial(materialDTO);

        materialRepository.save(material);

        MaterialDTO materialDTORetorno = materialToDTO(material);

        String chaveDeAcesso = material.getTurma().getChaveDeAcesso();

        pushNotificationService.sendPushNotification(new PushNotificationRequest(materialDTO.getTitulo(), materialDTO.getDescricao(), chaveDeAcesso));

        return materialDTORetorno;
    }

    private void validarDescricaoImagemLinks(MaterialDTO materialDTO) throws CampoObrigatorio {
        if (materialDTO.getDescricao() == null || materialDTO.getImagem() == null || materialDTO.getLinks().size() == 0 || materialDTO.getLinks() == null) {
            throw new CampoObrigatorio("A descrição ou imagem ou links são obrigatórios");
        }
    }

    private void validarTitulo(MaterialDTO materialDTO) throws CampoObrigatorio {
        if (materialDTO.getTitulo() == null || materialDTO.getTitulo().isEmpty()) {
            throw new CampoObrigatorio("O titulo é obrigatório");
        }
    }

    private void validarTurma(MaterialDTO materialDTO) throws CampoObrigatorio {
        if (materialDTO.getTurma_id() == null) {
            throw new CampoObrigatorio("O id da turma é obrigatório");
        }
    }

    private MaterialDTO materialToDTO(Material material) {
        MaterialDTO materialDTORetorno = new MaterialDTO();
        materialDTORetorno.setId(material.getId());
        materialDTORetorno.setTitulo(material.getTitulo());
        materialDTORetorno.setDescricao(material.getDescricao());
        materialDTORetorno.setImagem(material.getImagem());
        materialDTORetorno.setLinks(material.getLinks());
        materialDTORetorno.setTurma_id(material.getTurma().getId());
        materialDTORetorno.setDataDeCriacao(material.getDataDeCriacao());
        return materialDTORetorno;
    }


    private Material DTOToMaterial(MaterialDTO materialDTO) throws ObjetoNaoEncontrado {
        Material material = new Material();
        material.setTitulo(materialDTO.getTitulo());
        material.setDescricao(materialDTO.getDescricao());
        material.setImagem(materialDTO.getImagem());
        material.setLinks(materialDTO.getLinks());
        material.setTurma(turmaService.findById(materialDTO.getTurma_id()));
        material.setDataDeCriacao(materialDTO.getDataDeCriacao());
        return material;
    }

    public Page<MaterialDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String chaveDeAcesso) throws ObjetoNaoEncontrado {
        Turma turma = retornaTurma(chaveDeAcesso);
        List<MaterialDTO> materiais = findAllById(turma.getId());
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        PageImpl<MaterialDTO> pageRetorno = new PageImpl<MaterialDTO>(materiais, pageRequest, materiais.size());

        return pageRetorno;
    }

    public MaterialDTO update(MaterialDTO materialDTO) throws CampoObrigatorio, ObjetoNaoEncontrado {

        validarTurma(materialDTO);

        validarTitulo(materialDTO);

        validarDescricaoImagemLinks(materialDTO);

        Material materialSalvo = materialRepository.findById(materialDTO.getId()).orElseThrow(ObjetoNaoEncontrado::new);

        materialSalvo.setTitulo(materialDTO.getTitulo());
        materialSalvo.setDescricao(materialDTO.getDescricao());
        materialSalvo.setImagem(materialDTO.getImagem());
        materialSalvo.setLinks(materialDTO.getLinks());
        materialSalvo.setTurma(turmaService.findById(materialDTO.getTurma_id()));


        materialRepository.save(materialSalvo);

        return materialToDTO(materialSalvo);

    }

    public MaterialDTO findById(Integer id) throws ObjetoNaoEncontrado {
        Material material = materialRepository.findById(id).orElseThrow(ObjetoNaoEncontrado::new);

        MaterialDTO materialDTO = materialToDTO(material);

        return materialDTO;
    }

    @Transactional
    public List<MaterialDTO> findAllById(Integer idDaTurma) throws ObjetoNaoEncontrado {
        ArrayList<Material> materiais = new ArrayList<>();
        materiais = materialRepository.findByTurma(turmaService.findById(idDaTurma));
        ArrayList<MaterialDTO> materiaisDTO = new ArrayList<MaterialDTO>();
        for (Material material : materiais) {
            materiaisDTO.add(materialToDTO(material));
        }
        return materiaisDTO;
    }

    public void delete(Integer id) throws ObjetoNaoEncontrado {
        try {
            materialRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjetoNaoEncontrado("Material com id " + id + " não encontrado");
        }
    }

    private Turma retornaTurma(String chaveDeAcesso) throws ObjetoNaoEncontrado {
        List<Turma> turmas = turmaService.findAll();

        for (int i = 0; i < turmas.size(); i++) {
            if (chaveDeAcesso.equals(turmas.get(i).getChaveDeAcesso())) {
                return turmas.get(i);
            }
        }
        throw new ObjetoNaoEncontrado("Turma da chave: " + chaveDeAcesso + " não encontrada!");
    }
}
