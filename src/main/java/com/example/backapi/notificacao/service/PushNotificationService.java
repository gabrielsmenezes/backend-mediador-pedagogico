package com.example.backapi.notificacao.service;

import com.example.backapi.notificacao.firebase.FCMService;
import com.example.backapi.notificacao.model.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ExecutionException;
//TODO fazer o notificacao ser um listener
@Service
public class PushNotificationService {

    @Value("#{${app.notifications.defaults}}")
    private Map<String, String> defaults;

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public void sendPushNotification(PushNotificationRequest request) {
        try {
            fcmService.sendMessage(request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }


}
