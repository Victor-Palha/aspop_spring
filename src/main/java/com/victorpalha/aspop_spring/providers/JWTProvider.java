package com.victorpalha.aspop_spring.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.victorpalha.aspop_spring.domain.member.constants.MemberRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JWTProvider {
    @Value("${api.security.token}")
    private String jwt_main_token;

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(jwt_main_token);
    }

    public String generateMainToken(String subject, MemberRole role, Instant expires) {
        Algorithm algorithm = this.getAlgorithm();
        return JWT.create()
                .withSubject(subject)
                .withIssuer("aspop-auth")
                .withClaim("role", role.name())
                .withExpiresAt(expires)
                .sign(algorithm);
    }

    public DecodedJWT verify(String token) {
        Algorithm algorithm = this.getAlgorithm();
        final String jwt = token.replace("Bearer ", "").trim();

        return JWT.require(algorithm)
                .build()
                .verify(jwt);
    }

    public DecodedJWT decodeTokenWithoutValidation(String token) {
        token = token.replace("Bearer ", "").trim();
        return JWT.decode(token);
    }
}
