package com.super_mercado.backend.validations;

import java.util.List;

import com.super_mercado.backend.dtos.requests.BuyProductRequestDTO;

public interface BuyValidation {
	void validateBuy(List<BuyProductRequestDTO> buyProductRequestDTOs);
}