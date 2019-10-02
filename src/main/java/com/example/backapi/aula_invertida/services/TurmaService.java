package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.example.backapi.aula_invertida.repositories.TurmaRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaRepository;


    public Turma save(Turma turma) throws CampoObrigatorio {
        verificarSeExisteNome(turma);
        verificarSeExisteChaveDeAcesso(turma);

        verificarSeNomeDaTurmaJaExiste(turma);

        verificarSeChaveDeAcessoJaExiste(turma);

        turma.adicionarNovaChave(turma.getChaveDeAcesso());

        Turma turmaCadastrada = turmaRepository.save(turma);


        return turmaCadastrada;
    }

    private void verificarSeExisteChaveDeAcesso(Turma turma) throws CampoObrigatorio {
        if (turma.getChaveDeAcesso() == null){
            throw new CampoObrigatorio("A chave de acesso é necessária");
        }
    }

    private void verificarSeExisteNome(Turma turma) throws CampoObrigatorio {
        if (turma.getNome() == null || turma.getNome().isEmpty()){
            throw new CampoObrigatorio("Nome da turma é necessário");
        }
    }

    private void verificarSeChaveDeAcessoJaExiste(Turma turma) {
        List<Turma> turmas = findAll();
        for (int i = 0; i < turmas.size(); i++){
            if (turma.getChaveDeAcesso().equals(turmas.get(i).getChaveDeAcesso()) && (turmas.get(i).getId() != turma.getId())){
                throw new ConstraintViolationException("Chave de acesso já cadastrado", null);
            }
        }
    }

    private void verificarSeNomeDaTurmaJaExiste(Turma turma) {
        List<Turma> turmas = findAll();
        for (int i = 0; i < turmas.size(); i++){
            if (turma.getNome().equals(turmas.get(i).getNome()) && turmas.get(i).getId() != turma.getId()){
                throw new ConstraintViolationException("Nome já cadastrado", null);
            }
        }
    }

    public List<Turma> findAll(){
        return turmaRepository.findAll();
    }

    @Transactional
    public Turma update(Turma turma) throws CampoObrigatorio, ObjetoNaoEncontrado {
        verificarSeExisteNome(turma);
        verificarSeExisteChaveDeAcesso(turma);
        verificarSeNomeDaTurmaJaExiste(turma);
        verificarSeChaveDeAcessoJaExiste(turma);
        Turma turma_retornada = turmaRepository.findById(turma.getId()).orElseThrow(ObjetoNaoEncontrado::new);
        if(turma_retornada.chaveFoiUsadaRecentemente(turma.getChaveDeAcesso())){
            throw new ConstraintViolationException("Esta chave foi usada recentemente, use outra", null);
        }
        turma.setUltimasChaves(turma_retornada.getUltimasChaves());
        turma.adicionarNovaChave(turma.getChaveDeAcesso());
        return turmaRepository.save(turma);
    }

    public Turma findById(Integer id) throws ObjetoNaoEncontrado {
        return turmaRepository.findById(id).orElseThrow(ObjetoNaoEncontrado::new);
    }

    public void delete(Integer id) throws ObjetoNaoEncontrado {
        try{
            turmaRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ObjetoNaoEncontrado("Turma não encontrada");
        }
    }
}
