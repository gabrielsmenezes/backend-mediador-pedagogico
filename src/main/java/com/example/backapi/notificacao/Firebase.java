package com.example.backapi.notificacao;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class Firebase {
    private final FirebaseApp firebaseApp;
    private FirebaseMessaging firebaseMessaging;

    public Firebase() throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream("/home/gabriel/Documentos/back-api/src/main/java/com/example/backapi/notificacao/mediador-pedagogico-firebase-adminsdk-ql6me-9586436c1c.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://mediador-pedagogico.firebaseio.com")
                .build();

        firebaseApp = FirebaseApp.initializeApp(options);

        firebaseMessaging = FirebaseMessaging.getInstance(firebaseApp);
    }

    public void sendMessage(String topico, String messagem) throws FirebaseMessagingException {
        Message message = Message.builder().setTopic(topico).putData("mensagem", messagem).build();

        String response = firebaseMessaging.send(message);

        System.out.println(response);
        System.out.println();
    }
}
