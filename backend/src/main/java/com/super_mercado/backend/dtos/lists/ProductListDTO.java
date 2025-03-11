package com.super_mercado.backend.dtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDTO {
	private String barcode; 
	private Integer amount;
}