package br.com.carlosjunior.authapi.infra.controllers;

import br.com.carlosjunior.authapi.domain.dtos.AuthenticationDTO;
import br.com.carlosjunior.authapi.domain.dtos.UserRegisterDTO;
import br.com.carlosjunior.authapi.domain.dtos.UserResponseDTO;
import br.com.carlosjunior.authapi.domain.services.impl.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;

    private final AuthorizationService service;

    public AuthorizationController(AuthenticationManager authenticationManager,
                                   AuthorizationService service) {
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterDTO dto) {
        return new ResponseEntity<>(service.register(dto), CREATED);
    }


}
