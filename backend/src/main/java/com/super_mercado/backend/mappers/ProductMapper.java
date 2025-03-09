package com.super_mercado.backend.mappers;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProdutcResponseDTO;
import com.super_mercado.backend.entities.Product;

public interface ProductMapper {
	Product toProduct(ProductRequestDTO dto);
	Product toProduct(String id, ProductRequestDTO dto);
	ProdutcResponseDTO toProdutcResponseDTO(Product product);
}