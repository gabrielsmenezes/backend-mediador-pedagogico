package com.example.backapi.config;

import com.example.backapi.aula_invertida.services.AlunoService;
import com.example.backapi.aula_invertida.services.MaterialService;
import com.example.backapi.aula_invertida.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncTaskConfig {

    @Autowired
    TurmaService turmaService;

    @Autowired
    MaterialService materialService;

    @Autowired
    AlunoService alunoService;

    @Scheduled(cron = "0 0 9 25 12 *")
    public void apagarTodasAsTurmas() {
        alunoService.deleteAll();
        materialService.deleteAll();
        turmaService.deleteAll();
    }

}
