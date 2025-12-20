package me.pinitnotification;

import me.pinitnotification.infrastructure.authenticate.JwtTokenProvider;
import me.pinitnotification.utils.TestKeys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

import java.security.PublicKey;

@ActiveProfiles("test")
@SpringBootTest
class PinitNotificationApplicationTests {

    @Test
    void contextLoads() {
    }

    @TestConfiguration
    static class TestBeans {
        @Bean
        @Primary
        JwtTokenProvider testJwtTokenProvider() {
            return new JwtTokenProvider(TestKeys.publicKey());
        }
    }
}
