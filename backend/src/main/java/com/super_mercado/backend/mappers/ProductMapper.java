package com.super_mercado.backend.mappers;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProdutcResponseDTO;
import com.super_mercado.backend.entities.Product;

public interface ProductMapper {
	Product toProdut(ProductRequestDTO dto);
	ProdutcResponseDTO toProdutcResponseDTO(Product product);
}