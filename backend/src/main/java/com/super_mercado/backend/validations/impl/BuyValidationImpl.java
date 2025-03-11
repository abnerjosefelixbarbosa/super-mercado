package com.super_mercado.backend.validations.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.requests.BuyProductRequestDTO;
import com.super_mercado.backend.repositories.ProductRepository;
import com.super_mercado.backend.validations.BuyValidation;

@Component
public class BuyValidationImpl implements BuyValidation {
	@Autowired
	private ProductRepository productRepository;
	
	public void validateBuy(List<BuyProductRequestDTO> buyProductRequestDTOs) {
		buyProductRequestDTOs.forEach((i) -> {
			boolean isExists = productRepository.existsByBarcode(i.getBarcode());
			if (!isExists)
				throw new RuntimeException("Produto deve existir.");
		});
	}
}