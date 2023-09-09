package br.com.carlosjunior.authapi.domain.dtos;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthenticationDTO {

    @NotEmpty(message = "login is mandatory")
    private String login;

    @NotEmpty(message = "password is mandatory")
    private String password;


}
