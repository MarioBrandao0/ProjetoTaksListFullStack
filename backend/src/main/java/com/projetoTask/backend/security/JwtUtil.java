package com.projetoTask.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String PALAVRA_CHAVE = "palavraChaveSuperSecretaQueNinguemSabeQualE";

    private final long TEMPO_EXPIRACAO = 1000 * 60 * 60 * 10;

    private Key getKey(){
        return Keys.hmacShaKeyFor(PALAVRA_CHAVE.getBytes());
    }

    public String generateToken(Long id, String nome) {
        return Jwts.builder()
                .setSubject(Long.toString(id))
                .claim("idUser", id)
                .claim("nomeUser", nome)
                .setExpiration(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public Long extractId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("idUser", Long.class);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
