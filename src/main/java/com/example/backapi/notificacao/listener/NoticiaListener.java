package com.example.backapi.notificacao.listener;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.notificacao.model.PushNotificationRequest;
import com.example.backapi.notificacao.service.PushNotificationService;
import org.jsoup.Jsoup;
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

            String titulo = noticia.getTitulo();
            titulo = Jsoup.parse(titulo).text();

            String descricao = noticia.getDescricao();
            if (descricao != null){
                descricao = Jsoup.parse(descricao).text();
            }


            if (!"test".equals(perfil.toString())){
                try {
                    pushNotificationService.sendPushNotification(new PushNotificationRequest(titulo, descricao, "Noticias"));
                } catch (NullPointerException n){
                    LOGGER.info("context");
                }
            }
        }
    }
