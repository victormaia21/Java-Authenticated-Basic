package br.com.carlosjunior.authapi.domain.services;

import br.com.carlosjunior.authapi.domain.dtos.UserResponseDTO;
import br.com.carlosjunior.authapi.domain.dtos.UserRegisterDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthorizationService extends UserDetailsService {

    UserResponseDTO register(UserRegisterDTO dto);

}
