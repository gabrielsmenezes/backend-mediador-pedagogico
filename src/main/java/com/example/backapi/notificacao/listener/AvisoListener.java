package com.example.backapi.notificacao.listener;

import com.example.backapi.aula_invertida.domain.material.Material;
import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.notificacao.model.PushNotificationRequest;
import com.example.backapi.notificacao.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;

public class AvisoListener {

    @Autowired
    PushNotificationService pushNotificationService;

    @PostPersist
    void posCreate(Aviso aviso) {
        pushNotificationService.sendPushNotification(new PushNotificationRequest(aviso.getTitulo(),aviso.getDescricao(),"Avisos"));
    }

}