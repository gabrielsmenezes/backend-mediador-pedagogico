package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
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
        verificarSeExisteNome(turma.getNome());
        verificarSeExisteChaveDeAcesso(turma.getChaveDeAcesso());

        verificarSeNomeDaTurmaJaExiste(turma);

        verificarSeChaveDeAcessoJaExiste(turma);

        turma.adicionarNovaChave(turma.getChaveDeAcesso());

        return turmaRepository.save(turma);
    }

    private void verificarSeExisteChaveDeAcesso(String chaveDeAcesso) throws CampoObrigatorio {
        if (chaveDeAcesso == null){
            throw new CampoObrigatorio("A chave de acesso é necessária");
        }
    }

    private void verificarSeExisteNome(String nome) throws CampoObrigatorio {
        if (nome == null || nome.isEmpty()){
            throw new CampoObrigatorio("Nome da turma é necessário");
        }
    }

    private void verificarSeChaveDeAcessoJaExiste(Turma turma) {
        List<Turma> turmas = findAll();
        for (int i = 0; i < turmas.size(); i++){
            if (turma.getChaveDeAcesso().equals(turmas.get(i).getChaveDeAcesso()) && (!turmas.get(i).getId().equals(turma.getId()))){
                throw new ConstraintViolationException("Chave de acesso já cadastrado", null);
            }
        }
    }

    private void verificarSeNomeDaTurmaJaExiste(Turma turma) {
        List<Turma> turmas = findAll();
        for (int i = 0; i < turmas.size(); i++){
            if (turma.getNome().equals(turmas.get(i).getNome()) && !turmas.get(i).getId().equals(turma.getId())){
                throw new ConstraintViolationException("Nome já cadastrado", null);
            }
        }
    }

    public List<Turma> findAll(){
        return turmaRepository.findAll();
    }

    @Transactional
    public Turma update(Turma turma) throws CampoObrigatorio, ObjetoNaoEncontrado {
        verificarSeExisteNome(turma.getNome());
        verificarSeExisteChaveDeAcesso(turma.getChaveDeAcesso());
        verificarSeNomeDaTurmaJaExiste(turma);
        verificarSeChaveDeAcessoJaExiste(turma);

        Turma turmaRetornada = turmaRepository.findById(turma.getId()).orElseThrow(ObjetoNaoEncontrado::new);

        if(turmaRetornada.chaveFoiUsadaRecentemente(turma.getChaveDeAcesso())){
            throw new ConstraintViolationException("Esta chave foi usada recentemente, use outra", null);
        }

        turma.setUltimasChaves(turmaRetornada.getUltimasChaves());
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

    public List<Aluno> findAlunoByTurmaId(Integer idDaTurma) throws ObjetoNaoEncontrado {

        Turma turma = findById(idDaTurma);

        return turma.getAlunos();

    }

    public void deleteAll(){
        turmaRepository.deleteAll();
    }
}