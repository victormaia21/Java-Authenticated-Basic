package br.com.carlosjunior.authapi.domain.services.impl;

import br.com.carlosjunior.authapi.domain.dtos.UserResponseDTO;
import br.com.carlosjunior.authapi.domain.dtos.UserRegisterDTO;
import br.com.carlosjunior.authapi.domain.entities.User;
import br.com.carlosjunior.authapi.domain.repositories.UserRepository;
import br.com.carlosjunior.authapi.domain.services.IAuthorizationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements IAuthorizationService {

    private final UserRepository repository;

    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }


    @Override
    public UserResponseDTO register(UserRegisterDTO dto) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User newUser = new User(dto.getLogin(), encryptedPassword, dto.getRole());
        return new UserResponseDTO(repository.save(newUser));
    }
}
