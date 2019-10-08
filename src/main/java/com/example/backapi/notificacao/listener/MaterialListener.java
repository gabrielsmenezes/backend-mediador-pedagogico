package com.example.backapi.notificacao.listener;

import com.example.backapi.aula_invertida.domain.material.Material;
import com.example.backapi.notificacao.model.PushNotificationRequest;
import com.example.backapi.notificacao.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;

public class MaterialListener {

    @Autowired
    PushNotificationService pushNotificationService;

    @PostPersist
    void posCreate(Material material) {
        pushNotificationService.sendPushNotification(new PushNotificationRequest(material.getTitulo(), material.getDescricao(), material.getTurma().getChaveDeAcesso()));

    }
}
