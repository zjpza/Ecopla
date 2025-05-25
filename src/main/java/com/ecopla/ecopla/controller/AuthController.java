package com.ecopla.ecopla.controller;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.ecopla.ecopla.model.Usuario;
import com.ecopla.ecopla.service.UsuarioService;
import com.ecopla.ecopla.security.JwtUtils;

record SignupRequest(String nome, String email, String senha, Integer idade) {}
record LoginRequest(String email, String senha) {}
record JwtResponse(String token, String tipo, String id, String nome, String email) {}

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final UsuarioService service;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authManager,
                          UsuarioService service,
                          JwtUtils jwtUtils) {
        this.authManager = authManager;
        this.service     = service;
        this.jwtUtils    = jwtUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<Usuario> signup(@Valid @RequestBody SignupRequest req) {
        Usuario u = new Usuario(null, req.nome(), req.email(), req.idade(), req.senha(), null);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.registerUser(u));
    }

    @PostMapping("/signup-admin")
    public ResponseEntity<Usuario> signupAdmin(@Valid @RequestBody SignupRequest req) {
        Usuario u = new Usuario(null, req.nome(), req.email(), req.idade(), req.senha(), null);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.registerAdmin(u));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.senha()));
        String token = jwtUtils.generateJwtToken(auth);
        var u = service.repo.findByEmail(req.email()).get();
        return ResponseEntity.ok(new JwtResponse(
                token, "Bearer", u.getId(), u.getNome(), u.getEmail()));
    }
}