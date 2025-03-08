package com.super_mercado.backend.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.repositories.ProductRepository;
import com.super_mercado.backend.validations.ProductValidation;

@Component
public class ProductValidationImpl implements ProductValidation {
	@Autowired
	private ProductRepository productRepository;
	
	public void validateProduct(Product product) {
		boolean isBarcodeExists = productRepository.existsByBarcode(product.getBarcode());
		
		if (isBarcodeExists)
			throw new RuntimeException("Codigo de barra não deve ser repetido.");
		if (product.getPrice().precision() != 2)
			throw new RuntimeException("Preço deve ter 2 dígitos de escala.");	
	}
}