package com.super_mercado.backend.dtos.requests;

import com.super_mercado.backend.entities.BuyProductId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyProductRequestDTO {
	private BuyProductId buyProductId;
	private BuyRequestDTO buyRequestDTO;
	private ProductRequestDTO productRequestDTO;
	private Integer amount;
}
