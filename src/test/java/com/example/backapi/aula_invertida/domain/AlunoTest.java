package com.example.backapi.aula_invertida.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlunoTest {

    @Test
    public void aluno_deve_ter_nome(){
        Aluno aluno = new Aluno();
        String nomeEsperado = "Gabriel Menezes";

        aluno.setNome(nomeEsperado);
        String nomeRetornado = aluno.getNome();

        assertEquals(nomeEsperado, nomeRetornado);
    }

    @Test
    public void aluno_deve_ter_chaveDeAcesso(){
        Aluno aluno = new Aluno();
        String chaveEsperada = "matematica2";

        aluno.setChaveDeAcesso(chaveEsperada);
        String chaveRetornada = aluno.getChaveDeAcesso();

        assertEquals(chaveEsperada, chaveRetornada);
    }

    @Test
    public void aluno_deve_ter_turma(){
        Aluno aluno = new Aluno();
        Turma turmaEsperada = new Turma();

        aluno.setTurma(turmaEsperada);
        Turma turmaRetornada = aluno.getTurma();

        assertEquals(turmaEsperada, turmaRetornada);
    }

}