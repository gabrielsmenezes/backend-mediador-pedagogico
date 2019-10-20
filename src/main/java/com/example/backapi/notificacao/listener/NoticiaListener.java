package com.example.backapi.notificacao.listener;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.notificacao.model.PushNotificationRequest;
import com.example.backapi.notificacao.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.persistence.PostPersist;
import java.util.logging.Logger;

public class NoticiaListener {

        @Autowired
        PushNotificationService pushNotificationService;

        @Autowired
        Environment environment;

        private static final Logger LOGGER = Logger.getLogger("logger");

        @PostPersist
        void posCreate(Noticia noticia){
            StringBuilder perfil = new StringBuilder();
            String[] vetor = environment.getActiveProfiles();
            for (String s : vetor) perfil.append(s);
            if (!"test".equals(perfil.toString())){
                try {
                    pushNotificationService.sendPushNotification(new PushNotificationRequest(noticia.getTitulo(), noticia.getDescricao(), "Noticias"));
                } catch (NullPointerException n){
                    LOGGER.info("context");
                }
            }
        }
    }
