package com.example.backapi.notificacao.firebase;

import com.example.backapi.notificacao.model.PushNotificationRequest;
import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {

    public void sendMessage(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithData(request);
        sendAndGetResponse(message);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                        .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message getPreconfiguredMessageWithData(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).putData("titulo", request.getTitle())
                .putData("descricao", request.getMessage()).setTopic(request.getTopic())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage()));
    }

}
