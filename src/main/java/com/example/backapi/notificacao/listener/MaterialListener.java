package com.example.backapi.notificacao.listener;

import com.example.backapi.aula_invertida.domain.material.Material;
import com.example.backapi.notificacao.model.PushNotificationRequest;
import com.example.backapi.notificacao.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.persistence.PostPersist;

public class MaterialListener {

    @Autowired
    PushNotificationService pushNotificationService;

    @Autowired
    Environment environment;

    @PostPersist
    void posCreate(Material material) {
        StringBuilder perfil = new StringBuilder();
        String[] vetor = environment.getActiveProfiles();
        for (String s : vetor) perfil.append(s);
        if (!"test".equals(perfil.toString())){
            pushNotificationService.sendPushNotification(new PushNotificationRequest(material.getTitulo(), material.getDescricao(), material.getTurma().getChaveDeAcesso()));
        }
    }
}
