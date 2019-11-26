package com.example.backapi.biblioteca.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemTopicoDTO {
    private Integer idDoTopico;
    private Integer id;
    private String nome;
    private String linkDoItem;

}
