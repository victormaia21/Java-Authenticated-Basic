package br.com.victor.authapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.victor.authapi.domain.dtos.LoginDto;
import br.com.victor.authapi.domain.dtos.RegisterUserDto;
import br.com.victor.authapi.domain.entities.User;
import br.com.victor.authapi.domain.services.UserService;
import br.com.victor.authapi.responses.Message;
import br.com.victor.authapi.responses.Token;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
@Tag(name = "User", description = "Endpoint para user e autenticação")
public class UserController {
	
	@Autowired
	UserService service;

	@Operation(summary = "Register a new user")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "User registered successfully", 
		             content = @Content(schema = @Schema(implementation = User.class))),
		@ApiResponse(responseCode = "409", description = "User already exists")
	})
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Valid RegisterUserDto user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.register(user));
	}
	
	@Operation(summary = "Login a user")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "User logged in successfully", 
		             content = @Content(schema = @Schema(implementation = Token.class))),
		@ApiResponse(responseCode = "409", description = "User not found or password is invalid")
	})
	@PostMapping("/login")
	public ResponseEntity<Token> login(@RequestBody @Valid LoginDto login) {
		return ResponseEntity.ok().body(service.login(login));
	}
	
	@Operation(summary = "Get current user information", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "User information retrieved successfully", 
		             content = @Content(schema = @Schema(implementation = User.class))),
		@ApiResponse(responseCode = "404", description = "User not found")
	})
	@GetMapping("/myUser")
	public ResponseEntity<User> myUser() {
		return ResponseEntity.ok().body(service.myUser());
	}
	
	@Operation(summary = "Update current user information", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "User information updated successfully", 
		             content = @Content(schema = @Schema(implementation = User.class))),
		@ApiResponse(responseCode = "409", description = "User not found or email already exists")
	})
	@PutMapping
	public ResponseEntity<User> updateMyUser(@RequestBody @Valid RegisterUserDto user) {
		return ResponseEntity.ok().body(service.updateMyUser(user));
	}
	
	@Operation(summary = "Delete current user", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "User deleted successfully", 
		             content = @Content(schema = @Schema(implementation = Message.class))),
		@ApiResponse(responseCode = "409", description = "User not found")
	})
	@DeleteMapping
	public ResponseEntity<Message> deleteUser() {
		return ResponseEntity.ok().body(service.deleteUser());
	}
}
