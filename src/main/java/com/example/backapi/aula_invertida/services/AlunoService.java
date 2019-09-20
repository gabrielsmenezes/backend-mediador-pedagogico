package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.Aluno;
import com.example.backapi.aula_invertida.domain.Turma;
import com.example.backapi.aula_invertida.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    TurmaService turmaService;

    @Autowired
    AlunoRepository alunoRepository;

    public Aluno save(Aluno aluno) throws Exception {

        Turma turma = retornaTurmaDoAluno(aluno);

        aluno.setTurma(turma);

        alunoRepository.save(aluno);

        return aluno;
    }

    private Turma retornaTurmaDoAluno(Aluno aluno) throws Exception {
        List<Turma> turmas = turmaService.findAll();

        for (int i = 0; i < turmas.size(); i++){
            if (aluno.getChaveDeAcesso().equals(turmas.get(i).getChaveDeAcesso())){
                return turmas.get(i);
            }
        }

        throw new Exception("Turma não encontrada");

    }
}
