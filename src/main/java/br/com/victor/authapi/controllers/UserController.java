package br.com.victor.authapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.authapi.domain.dtos.LoginDto;
import br.com.victor.authapi.domain.entities.User;
import br.com.victor.authapi.domain.services.UserService;
import br.com.victor.authapi.responses.Token;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	UserService service;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Valid User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.register(user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Token> login(@RequestBody @Valid LoginDto login) {
		return ResponseEntity.ok().body(service.login(login));
	}
	
	@GetMapping("/myUser")
	public ResponseEntity<User> myUser() {
		return ResponseEntity.ok().body(service.myUser());
	}
}
