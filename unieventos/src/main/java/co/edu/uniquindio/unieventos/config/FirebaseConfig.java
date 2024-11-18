package co.edu.uniquindio.unieventos.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp intializeFirebase() throws IOException {
        //InputStream serviceAccount = new ClassPathResource("unieventos-61dca-firebase-adminsdk-62w3k-7a90c133ca.json").getInputStream();

// Codigo anterior
      FileInputStream serviceAccount = new FileInputStream(
                "src/main/resources/unieventos-61dca-firebase-adminsdk-62w3k-7a90c133ca.json"
        );

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("unieventos-61dca.appspot.com")
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        }

        return null;
    }

}