package br.com.victor.authapi.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class LoginDto {

	@NotNull(message = "Email required")
	@Email(message = "Invalid email")
    private String email;

	@NotNull(message = "Password required")
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
