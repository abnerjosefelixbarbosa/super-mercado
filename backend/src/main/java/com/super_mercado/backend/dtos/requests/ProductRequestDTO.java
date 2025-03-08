package com.super_mercado.backend.dtos.requests;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
	@NotNull(message = "Codigo de barra não deve ser nulo.")
	@NotEmpty(message = "Codigo de barra não deve ser vazio.")
	@Size(max = 50, message = "Codigo de barra deve ter até 50 caracteres.")
	private String barcode;
	@NotNull(message = "Descrição não deve ser nula.")
	@NotEmpty(message = "Descrição não deve ser vazia.")
	@Size(max = 100, message = "Descrição deve ter até 100 caracteres.")
	private String description;
	@NotNull(message = "Preço não deve ser nulo")
	private BigDecimal price;
}
