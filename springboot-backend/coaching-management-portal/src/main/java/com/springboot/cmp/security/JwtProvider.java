package com.springboot.cmp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    // JWT secret
    private String jwtSecret = "";

    // JWT expiration in millis
    private Long jwtExpiration = 60 * 1000L;

    private Claims parseToken(String token) {

        // create JSON parser
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build();

        try {

            return jwtParser.parseClaimsJwt(token)
                    .getBody();
        } catch (
                ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException | MalformedJwtException |
                SignatureException e) {
        }
        return null;
    }

    public boolean validateToken(String token) {
        return parseToken(token) != null;
    }

    public String getUsernameFromToken(String token) {

        // get claims
        Claims claims = parseToken(token);

        // extract subject
        if (claims != null) return claims.getSubject();

        return null;
    }


    public String generateToken(String username) {

        // create signing key
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());


        // generate token
        var currentDate = new Date();

        var expiration = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))
                .signWith(key)
                .compact();
    }
}
