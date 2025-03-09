package com.super_mercado.backend.validations;

import java.util.List;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.entities.Buy;

public interface BuyValidation {
	void validateProduct(Buy buy, List<ProductRequestDTO> dtos);
}
