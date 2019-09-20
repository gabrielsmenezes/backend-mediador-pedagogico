package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.Aluno;
import com.example.backapi.aula_invertida.domain.Turma;
import com.example.backapi.aula_invertida.repositories.AlunoRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    TurmaService turmaService;

    @Autowired
    AlunoRepository alunoRepository;

    public Aluno save(Aluno aluno) throws CampoObrigatorio, ObjetoNaoEncontrado {

        if(aluno.getNomeDoAluno().isEmpty()){
            throw new CampoObrigatorio("Campo nome é obrigatório");
        }

        Turma turma = retornaTurmaDoAluno(aluno);

        aluno.setTurma(turma);

        alunoRepository.save(aluno);

        return aluno;
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
}
