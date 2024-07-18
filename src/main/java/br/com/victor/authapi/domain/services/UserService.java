package br.com.victor.authapi.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.victor.authapi.config.security.SecurityConfiguration;
import br.com.victor.authapi.config.security.SecurityFilter;
import br.com.victor.authapi.config.security.TokenService;
import br.com.victor.authapi.domain.dtos.LoginDto;
import br.com.victor.authapi.domain.dtos.RegisterUserDto;
import br.com.victor.authapi.domain.entities.User;
import br.com.victor.authapi.domain.repositories.UserRepository;
import br.com.victor.authapi.exception.CustomException;
import br.com.victor.authapi.responses.Message;
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
	
	public User register(RegisterUserDto userDto) {
		Optional<User> userExist = repository.findUserByEmail(userDto.getEmail());	
		
		if(userExist.isPresent()) {
			throw new CustomException("User Exist", HttpStatus.CONFLICT);
		}
		
		userDto.setPassword(security.passwordEncoder().encode(userDto.getPassword()));
		
		User newUser = new User(null, userDto.getName(), userDto.getEmail(), userDto.getPassword());
		return repository.save(newUser);
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
	    String email = token.validateToken(tokenHeader);
	    Optional<User> userOptional = repository.findUserByEmail(email);

	    if (userOptional.isPresent()) {
	        return userOptional.get();
	    } else {
	        throw new CustomException("Usuário not found", HttpStatus.NOT_FOUND);
	    }
	}
	
	public User updateMyUser(RegisterUserDto newUser) {
		String tokenHeader = securityFilter.recoverToken(request);
	    String email = token.validateToken(tokenHeader);
	    Optional<User> userOptional = repository.findUserByEmail(email);
	    
	    if(userOptional.isEmpty()) {
	    	throw new CustomException("User not found", HttpStatus.CONFLICT);
	    }
	    
	    Optional<User> emailExist = repository.findUserByEmail(newUser.getEmail());
	    
	    if(!newUser.getEmail().equals(userOptional.get().getEmail()) && emailExist.isPresent()) {
	    	throw new CustomException("Email exist", HttpStatus.CONFLICT);
	    }
	    
	    userOptional.get().setEmail(newUser.getEmail());
	    
	  
	    
	    if (newUser.getName() != null) {
	    	userOptional.get().setName(newUser.getName());
	    }
		
		if (newUser.getPassword() != null) {
			userOptional.get().setPassword(security.passwordEncoder().encode(newUser.getPassword()));
		}
		
		return repository.save(userOptional.get());
	}
	
	public Message deleteUser() {
		String tokenHeader = securityFilter.recoverToken(request);
	    String email = token.validateToken(tokenHeader);
	    Optional<User> userOptional = repository.findUserByEmail(email);
	    
	    if(userOptional.isEmpty()) {
	    	throw new CustomException("User not found", HttpStatus.CONFLICT);
	    }
	    
	    repository.delete(userOptional.get());
	    
	    return new Message("User delete sussefuly");
	}

}
