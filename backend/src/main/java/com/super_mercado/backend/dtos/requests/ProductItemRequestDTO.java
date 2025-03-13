package com.super_mercado.backend.dtos.requests;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemRequestDTO {
	private String barcode; 
	private String description;
	private BigDecimal price;
	private Integer amount;
}