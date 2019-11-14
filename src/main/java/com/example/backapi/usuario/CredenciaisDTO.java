package com.example.backapi.usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class CredenciaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String senha;

}
