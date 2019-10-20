package com.example.backapi.aula_invertida.domain.material;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkMaterial implements Serializable {
    @Column
    private String link;

    @Column
    private String nome;

}
