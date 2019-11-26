package com.example.backapi.notificacao.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PushNotificationRequestTest {

    PushNotificationRequest pushNotificationRequest;
    String title;
    String message;
    String topic;

    @Before
    public void setUp() throws Exception {
        title = "Titulo";
        message = "Message";
        topic = "Topic";
    }

    @Test
    public void getTitle() {
        pushNotificationRequest = new PushNotificationRequest(title,message,topic);
        assertEquals(title, pushNotificationRequest.getTitle());
    }

    @Test
    public void getMessage() {
        pushNotificationRequest = new PushNotificationRequest(title,message,topic);
        assertEquals(message, pushNotificationRequest.getMessage());
    }

    @Test
    public void getTopic() {
        pushNotificationRequest = new PushNotificationRequest(title,message,topic);
        assertEquals(topic, pushNotificationRequest.getTopic());
    }
}