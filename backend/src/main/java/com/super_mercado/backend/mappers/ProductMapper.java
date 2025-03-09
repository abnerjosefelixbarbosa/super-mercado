package com.super_mercado.backend.mappers;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProductResponseDTO;
import com.super_mercado.backend.entities.Product;

public interface ProductMapper {
	Product toProduct(ProductRequestDTO dto);
	Product toProduct(String id, ProductRequestDTO dto);
	ProductResponseDTO toProdutcResponseDTO(Product product);
}