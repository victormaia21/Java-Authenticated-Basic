package br.com.carlosjunior.authapi.infra.controllers;

import br.com.carlosjunior.authapi.config.security.TokenService;
import br.com.carlosjunior.authapi.domain.dtos.AuthenticationDTO;
import br.com.carlosjunior.authapi.domain.dtos.LoginResponseDTO;
import br.com.carlosjunior.authapi.domain.dtos.UserRegisterDTO;
import br.com.carlosjunior.authapi.domain.dtos.UserResponseDTO;
import br.com.carlosjunior.authapi.domain.entities.User;
import br.com.carlosjunior.authapi.domain.services.impl.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new ResponseEntity<>(new LoginResponseDTO(token), OK);
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterDTO dto) {
        return new ResponseEntity<>(service.register(dto), CREATED);
    }


}
