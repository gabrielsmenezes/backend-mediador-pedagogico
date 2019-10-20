package com.example.backapi.notificacao.listener;

import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.notificacao.model.PushNotificationRequest;
import com.example.backapi.notificacao.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.persistence.PostPersist;
import java.util.Arrays;

public class AvisoListener {

    @Autowired
    PushNotificationService pushNotificationService;

    @Autowired
    Environment environment;

    @PostPersist
    void posCreate(Aviso aviso) {
        StringBuilder perfil = new StringBuilder();
        String[] vetor = environment.getActiveProfiles();
        for (String s : vetor) perfil.append(s);
        if (!"test".equals(perfil.toString())){
            pushNotificationService.sendPushNotification(new PushNotificationRequest(aviso.getTitulo(),aviso.getDescricao(),"Avisos"));
        }
    }

}