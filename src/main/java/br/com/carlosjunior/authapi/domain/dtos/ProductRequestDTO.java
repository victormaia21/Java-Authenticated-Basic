package br.com.carlosjunior.authapi.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    @NotEmpty(message = "Name is mandatory")
    private String name;


    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Double price;
}
