package com.super_mercado.backend.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.services.ProductService;
import com.super_mercado.backend.validations.BuyValidation;

@Component
public class BuyValidationImpl implements BuyValidation {
	@Autowired
	private ProductService productService;
	
	public void validateBuy(Buy buy) {
		buy.getBuyProducts().forEach((i) -> {
			String barcode = i.getProduct().getBarcode();
			Integer amount = i.getAmount();
			if (amount == null || amount != 1)
				throw new RuntimeException("Quantidade não deve ser nula ou diferente de 1.");
			if (barcode.isEmpty() || barcode.equals(null))
				throw new RuntimeException("Codigo de barra do produto não deve ser vazio ou nulo.");
			boolean isExists = productService.existsByBarcode(barcode);
			if (!isExists)
				throw new RuntimeException("Produto deve existir.");
		});
	}
}