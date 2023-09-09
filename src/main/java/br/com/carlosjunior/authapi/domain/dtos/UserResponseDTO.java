package br.com.carlosjunior.authapi.domain.dtos;

import br.com.carlosjunior.authapi.domain.entities.User;
import br.com.carlosjunior.authapi.domain.enums.RolesEnum;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String login;
    private RolesEnum role;

    public UserResponseDTO(User entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.role = entity.getRole();
    }
}
