package br.com.victor.authapi.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.victor.authapi.config.security.SecurityConfiguration;
import br.com.victor.authapi.config.security.SecurityFilter;
import br.com.victor.authapi.config.security.TokenService;
import br.com.victor.authapi.domain.dtos.LoginDto;
import br.com.victor.authapi.domain.entities.User;
import br.com.victor.authapi.domain.repositories.UserRepository;
import br.com.victor.authapi.enums.Role;
import br.com.victor.authapi.exception.CustomException;
import br.com.victor.authapi.responses.Token;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private SecurityConfiguration security;
	
	@Autowired
	private TokenService token;
	
	@Autowired
	SecurityFilter securityFilter;
	
	@Autowired
	HttpServletRequest request;
	
	public User register(User user) {
		Optional<User> userExist = repository.findUserByEmail(user.getEmail());	
		
		if(userExist.isPresent()) {
			throw new CustomException("User Exist", HttpStatus.CONFLICT);
		}
		
		user.setPassword(security.passwordEncoder().encode(user.getPassword()));
		user.getRoles().add(Role.USER);
		return repository.save(user);
	}
	
	public Token login(LoginDto login) {
		Optional<User> user = repository.findUserByEmail(login.getEmail());
		
		if(user.isEmpty()) {
			throw new CustomException("User not found", HttpStatus.CONFLICT);
		}
		
		Boolean passwordIsValid = security.passwordEncoder().matches(login.getPassword(), user.get().getPassword());
		
		if(passwordIsValid != true) {
			throw new CustomException("Passowrd is not valid", HttpStatus.CONFLICT);
		}
		
		return new Token(token.generateToken(user.get()));
	}
	
	public User myUser() {
	    String tokenHeader = securityFilter.recoverToken(request);
	    System.out.println(tokenHeader);
	    String email = token.validateToken(tokenHeader);
	    Optional<User> userOptional = repository.findUserByEmail(email);

	    if (userOptional.isPresent()) {
	        return userOptional.get();
	    } else {
	        throw new CustomException("Usuário não encontrado", HttpStatus.NOT_FOUND);
	    }
	}

}
