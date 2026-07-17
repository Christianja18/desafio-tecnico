package com.christian.desafio.experience.security;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {
    @Test
    void shouldGenerateAndReadSignedToken() {
        JwtService service = new JwtService("01234567890123456789012345678901", Duration.ofHours(1));

        String token = service.generate("challenge-user");

        assertThat(service.parse(token).getSubject()).isEqualTo("challenge-user");
    }
}
