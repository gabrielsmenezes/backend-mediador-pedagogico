package com.example.backapi.notificacao.model;

public class PushNotificationRequest {

    private String title;
    private String message;
    private String topic;

    public PushNotificationRequest(String title, String messageBody, String topicName) {
        this.title = title;
        this.message = messageBody;
        this.topic = topicName;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTopic() {
        return topic;
    }

}
