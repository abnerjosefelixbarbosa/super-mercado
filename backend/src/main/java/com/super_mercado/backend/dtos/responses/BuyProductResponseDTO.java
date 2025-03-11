package com.super_mercado.backend.dtos.responses;

import com.super_mercado.backend.entities.BuyProductId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor	
public class BuyProductResponseDTO {
	private BuyProductId buyProductId;
	private BuyResponseDTO buy;
	private ProductResponseDTO product;
	private Integer amount;
}