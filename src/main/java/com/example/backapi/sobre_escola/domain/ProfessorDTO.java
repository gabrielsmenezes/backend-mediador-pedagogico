package com.example.backapi.sobre_escola.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorDTO {

    private Integer id;
    private String nome;
    private List<String> disciplinas = new ArrayList<>();



}
