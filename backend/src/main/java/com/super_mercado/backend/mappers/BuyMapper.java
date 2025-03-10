package com.super_mercado.backend.mappers;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.entities.Buy;

public interface BuyMapper {
	Buy toBuy(BuyRequestDTO dto);
	BuyResponseDTO toBuyResponseDTO(Buy buy);
}