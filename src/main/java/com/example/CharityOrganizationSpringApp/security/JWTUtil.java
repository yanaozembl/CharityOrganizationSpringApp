package com.example.CharityOrganizationSpringApp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt_secret}") // usually this secret string is in an external file
    private String secret;

    public String generateToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(6000).toInstant()); // toInstant - перевод в timestamp
        return JWT.create()
                .withSubject("User details")
                .withClaim("email", username) // key - value
                .withIssuedAt(new Date()) // current time of creating token
                .withIssuer("spring-app") // who issued the token
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    // JWTVerificationException - if token isn't verified
    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("spring-app")
                .build();

        DecodedJWT jwt = verifier.verify(token); // get jwt token with user details
        return jwt.getClaim("email").asString(); // get necessary information
    }
}
