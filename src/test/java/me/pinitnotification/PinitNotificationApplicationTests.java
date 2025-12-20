package me.pinitnotification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import me.pinitnotification.infrastructure.authenticate.JwtTokenProvider;
import me.pinitnotification.utils.TestKeys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.PublicKey;

@ActiveProfiles("test")
@SpringBootTest
class PinitNotificationApplicationTests {

    @Test
    void contextLoads() {
    }

    @TestConfiguration
    static class TestBeans {

        @MockitoBean
        FirebaseMessaging firebaseMessaging;

        @Bean
        @Primary
        JwtTokenProvider testJwtTokenProvider() {
            return new JwtTokenProvider(TestKeys.publicKey());
        }
    }
}
