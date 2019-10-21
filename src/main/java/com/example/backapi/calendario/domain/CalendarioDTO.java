package com.example.backapi.calendario.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CalendarioDTO {
    private Integer id;
    private String linkDoCalendario;
}
