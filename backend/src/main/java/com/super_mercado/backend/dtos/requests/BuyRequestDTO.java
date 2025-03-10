package com.super_mercado.backend.dtos.requests;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyRequestDTO {
	@Size(max = 20, message = "Documento do cliente deve ter até 20 caracteres.")
	private String customerDocment;
	@NotNull(message = "Lista não deve ser nulo.")
	private List<BuyProductRequestDTO> buyProductRequestDTOs;
}
