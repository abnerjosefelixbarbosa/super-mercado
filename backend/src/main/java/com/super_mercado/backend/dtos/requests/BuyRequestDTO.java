package com.super_mercado.backend.dtos.requests;

import java.util.List;

import com.super_mercado.backend.dtos.lists.ProductListDTO;

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
	private List<ProductListDTO> productListDTOs;
}
