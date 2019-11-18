package com.example.backapi.config;

import com.example.backapi.usuario.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	@Bean
	public boolean instantiateDatabase(DBService dbService) {
		dbService.instantiateTestDatabase();
		return true;
	}

}
