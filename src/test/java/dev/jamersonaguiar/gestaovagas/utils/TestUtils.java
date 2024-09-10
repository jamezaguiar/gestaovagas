package dev.jamersonaguiar.gestaovagas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class TestUtils {


    public static String toJSON(Object object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID id) {

        Algorithm algorithm = Algorithm.HMAC256("voce@nunca_vai#descobrir-o+segredo");

        var expiresAt = Instant.now().plus(Duration.ofHours(2));

        return JWT.create()
                .withIssuer("jamersonaguiar.dev")
                .withExpiresAt(expiresAt)
                .withSubject(id.toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);
    }

}
