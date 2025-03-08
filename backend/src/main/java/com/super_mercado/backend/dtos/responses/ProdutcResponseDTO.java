package com.super_mercado.backend.dtos.responses;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutcResponseDTO {
	private String id;
	private String barcode;
	private String description;
	private BigDecimal price;
}
