package com.example.backapi.notificacao.listener;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.notificacao.model.PushNotificationRequest;
import com.example.backapi.notificacao.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;
import java.util.logging.Logger;

public class NoticiaListener {

        @Autowired
        PushNotificationService pushNotificationService;

        private static final Logger LOGGER = Logger.getLogger("logger");

        @PostPersist
        void posCreate(Noticia noticia){
            try {
                pushNotificationService.sendPushNotification(new PushNotificationRequest(noticia.getTitulo(), noticia.getDescricao(), "Noticias"));
            } catch (NullPointerException n){
                LOGGER.info("context");
            }
        }
    }
