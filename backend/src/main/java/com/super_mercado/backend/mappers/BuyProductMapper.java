package com.super_mercado.backend.mappers;

import com.super_mercado.backend.dtos.responses.BuyProductResponseDTO;
import com.super_mercado.backend.entities.BuyProduct;

public interface BuyProductMapper {
	BuyProductResponseDTO toBuyProductResponseDTO(BuyProduct buyProduct);
}