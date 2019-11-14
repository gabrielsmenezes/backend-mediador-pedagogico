package com.example.backapi.config;

import com.example.backapi.usuario.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() {
		dbService.instantiateTestDatabase();
		return true;
	}

}
