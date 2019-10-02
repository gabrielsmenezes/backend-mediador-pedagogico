package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.example.backapi.aula_invertida.repositories.AlunoRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    TurmaService turmaService;

    @Autowired
    AlunoRepository alunoRepository;

    public Aluno save(Aluno aluno) throws CampoObrigatorio, ObjetoNaoEncontrado {

        verificarNome(aluno);

        verificarChaveDeAcesso(aluno);

        Turma turma = retornaTurmaDoAluno(aluno);

        List<Aluno> alunos = turma.getAlunos();

        for (Aluno aluno_da_turma : alunos){
            if (aluno_da_turma.getNome().equals(aluno.getNome())){
                return aluno_da_turma;
            }
        }

        aluno.setTurma(turma);

        alunoRepository.save(aluno);

        return aluno;
    }

    private void verificarChaveDeAcesso(Aluno aluno) throws CampoObrigatorio {
        if(aluno.getChaveDeAcesso() == null ||aluno.getChaveDeAcesso().isEmpty()){
            throw new CampoObrigatorio("Campo chave de acesso é obrigatório");
        }
    }

    private void verificarNome(Aluno aluno) throws CampoObrigatorio {
        if(aluno.getNome() == null || aluno.getNome().isEmpty()){
            throw new CampoObrigatorio("Campo nome é obrigatório");
        }
    }

    private Turma retornaTurmaDoAluno(Aluno aluno) throws ObjetoNaoEncontrado {
        List<Turma> turmas = turmaService.findAll();

        for (int i = 0; i < turmas.size(); i++){
            if (aluno.getChaveDeAcesso().equals(turmas.get(i).getChaveDeAcesso())){
                return turmas.get(i);
            }
        }

        throw new ObjetoNaoEncontrado("Turma de chave "+aluno.getChaveDeAcesso()+ " não encontrada");

    }

    public void delete(Integer id) throws ObjetoNaoEncontrado {
        try{
            alunoRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ObjetoNaoEncontrado("Nenhum aluno encontrado com id: " + id);
        }

    }

    public Aluno findById(Integer id) throws ObjetoNaoEncontrado {
        return alunoRepository.findById(id).orElseThrow(ObjetoNaoEncontrado::new);
    }
}
