package com.user.session.controller;

import com.user.session.dto.UserRequest;
import com.user.session.mapper.UserMapper;
import com.user.session.model.Usuario;
import com.user.session.service.UserSignin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserSignin userSignin;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastro(@RequestBody @Valid UserRequest dto) {
        Usuario user = UserMapper.toEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSignin.criar(user));
    }
}
