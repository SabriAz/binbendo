package com.informatica.infirfs_2026.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email, String role) throws IllegalArgumentException, JWTCreationException {
        // JWT token gets made based off user info + JWT Secret
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(this.createExpirationDate())
                .withIssuer("Duck Studios")
                .sign(Algorithm.HMAC256(secret));
    }
    // Validates JWT tokens based off when it was given, whether it was given by us or not etc.
    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("Duck Studios")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }
    // Makes sure JWT tokens have an expiry date
    private Date createExpirationDate(){
        int expirationHours = 6;
        Calendar appendableDate = Calendar.getInstance();
        appendableDate.setTime(new Date());
        appendableDate.add(Calendar.HOUR, expirationHours);
        return appendableDate.getTime();
    }
}
