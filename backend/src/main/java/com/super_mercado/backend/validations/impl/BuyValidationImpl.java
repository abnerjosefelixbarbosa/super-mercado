package com.super_mercado.backend.validations.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.repositories.ProductRepository;
import com.super_mercado.backend.validations.BuyValidation;

@Component
public class BuyValidationImpl implements BuyValidation {
	@Autowired
	private ProductRepository productRepository;
	
	public void validateProduct(Buy buy, List<ProductRequestDTO> dtos) {
		dtos.forEach((i) -> {
			boolean isExistsBarcode = productRepository.existsByBarcode(i.getBarcode());
			if (!isExistsBarcode) 
				throw new RuntimeException("Produto deve existir");
		});
	}
}
