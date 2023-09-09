package br.com.carlosjunior.authapi.domain.dtos;

import br.com.carlosjunior.authapi.domain.enums.RolesEnum;
import lombok.Data;

@Data
public class UserRegisterDTO extends AuthenticationDTO {
    private RolesEnum role;
}
