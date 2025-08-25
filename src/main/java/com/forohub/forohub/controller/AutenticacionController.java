package com.forohub.forohub.controller;

import com.forohub.forohub.dto.AutenticacionUsuarioDTO;
import com.forohub.forohub.dto.JWTTokenDTO;
import com.forohub.forohub.model.Usuario;
import com.forohub.forohub.repository.UsuarioRepository;
import com.forohub.forohub.service.TokenService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<JWTTokenDTO> autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datos.email(), datos.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTtoken));
    }
}