package br.com.victor.authapi.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class RegisterUserDto {

	@NotNull(message = "Name required")
	private String name;
	
	@NotNull(message = "Email required")
	@Email(message = "Invalid email")
	private String email;
	
	@NotNull(message = "Password required")
	private String password;
	
	
	public RegisterUserDto(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
