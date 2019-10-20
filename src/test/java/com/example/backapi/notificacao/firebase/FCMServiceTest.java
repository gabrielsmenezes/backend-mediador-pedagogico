package com.example.backapi.notificacao.firebase;

import com.example.backapi.notificacao.model.PushNotificationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FCMServiceTest {

    PushNotificationRequest pushNotificationRequest;

    @Autowired
    FCMService fcmService;

    @Test
    public void sendMessage() throws ExecutionException, InterruptedException {
        pushNotificationRequest = new PushNotificationRequest("Testando", "Envio de mensagem", "o");
        fcmService.sendMessage(pushNotificationRequest);
    }
}