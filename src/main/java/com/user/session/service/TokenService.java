package com.user.session.service;


import com.user.session.model.Usuario;
import org.springframework.stereotype.*;

@Service
public class TokenService {

    private String secret = "segredoSuperSeguro"; // ideal usar em application.properties

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String validarToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // retorna o e-mail
    }
}
