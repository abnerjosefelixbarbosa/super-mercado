package com.super_mercado.backend.services;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;

public interface BuyService {
	BuyResponseDTO registerBuy(BuyRequestDTO dto);
}