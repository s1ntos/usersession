package com.user.session.controller;


import com.user.session.model.Usuario;
import com.user.session.repository.UsuarioRepository;
import com.user.session.service.UserSignin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserSignin userSignin;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastro(@RequestBody Usuario usuario) {
        Usuario user = userSignin.criar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData, HttpSession session) {
        String email = loginData.get("email");
        String senha = loginData.get("senha");

        return usuarioRepository.findByEmail(email)
                .filter(user -> user.getSenha().equals(senha))
                .map(user -> {
                    session.setAttribute("usuario", user);
                    return ResponseEntity.ok(Map.of("mensagem", "Logado com sucesso"));
                })
                .orElse(ResponseEntity.status(401).body(Map.of("erro", "Credenciais inválidas")));
    }

    @GetMapping("/me")
    public ResponseEntity<?> dadosUsuario(HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user == null) return ResponseEntity.status(401).body("Não autenticado");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Deslogado com sucesso");
    }
}
