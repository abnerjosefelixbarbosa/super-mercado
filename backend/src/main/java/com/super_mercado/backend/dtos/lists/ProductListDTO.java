package com.super_mercado.backend.dtos.lists;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDTO {
	private String barcode; 
	private String description;
	private BigDecimal price;
	private Integer amount;
}