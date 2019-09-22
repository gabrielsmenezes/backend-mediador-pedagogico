package com.example.backapi.aula_invertida.domain;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.material.Material;
import com.example.backapi.aula_invertida.domain.turma.Turma;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TurmaTest {

    @Test
    public void turma_deve_ter_nome(){
        String nomeEsperado = "1°A";

        Turma turma = new Turma();
        turma.setNome(nomeEsperado);

        String nomeRetornado = turma.getNome();

        assertEquals(nomeEsperado, nomeRetornado);

    }

    @Test
    public void turma_deve_ter_chave_de_acesso(){

        String chaveEsperada = "senhaSecreta";

        Turma turma = new Turma();
        turma.setChaveDeAcesso(chaveEsperada);

        String chaveRetornada = turma.getChaveDeAcesso();

        assertEquals(chaveEsperada, chaveRetornada);

    }

    @Test
    public void turma_deve_ter_pode_conter_lista_de_materiais(){
        List<Material> materiaisEsperados = Arrays.asList(new Material(), new Material());

        Turma turma = new Turma();
        turma.setMateriais(materiaisEsperados);

        List<Material> materiaisRetornados = turma.getMateriais();

        assertEquals(materiaisEsperados, materiaisRetornados);

    }

    @Test
    public void turma_deve_ter_lista_de_alunos(){
        List<Aluno> alunosEsperados = Arrays.asList(new Aluno(), new Aluno());

        Turma turma = new Turma();
        turma.setAlunos(alunosEsperados);

        List<Aluno> alunosRetornados = turma.getAlunos();
        assertEquals(alunosEsperados, alunosRetornados);
    }

}