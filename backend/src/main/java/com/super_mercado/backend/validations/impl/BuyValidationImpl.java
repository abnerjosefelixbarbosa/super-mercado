package com.super_mercado.backend.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.repositories.ProductRepository;
import com.super_mercado.backend.validations.BuyValidation;

@Component
public class BuyValidationImpl implements BuyValidation {
	@Autowired
	private ProductRepository productRepository;
	
	public void validateBuy(Buy buy) {
		buy.getBuyProducts().forEach((i) -> {
			String barcode = i.getProduct().getBarcode();
			boolean isExists = productRepository.existsByBarcode(barcode);
			if (!isExists)
				throw new RuntimeException("Produto deve existir.");
		});
	}
}