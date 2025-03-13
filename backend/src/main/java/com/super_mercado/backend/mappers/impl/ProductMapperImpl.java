package com.super_mercado.backend.mappers.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProductResponseDTO;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.mappers.ProductMapper;

@Component
public class ProductMapperImpl implements ProductMapper {
	public Product toProduct(ProductRequestDTO dto) {
		Product product = new Product(UUID.randomUUID().toString(), dto.getBarcode(), dto.getDescription(),
				dto.getPrice(), null);
		return product;
	}

	public Product toProduct(String id, ProductRequestDTO dto) {
		Product product = new Product(id, dto.getBarcode(), dto.getDescription(), dto.getPrice(), null);
		return product;
	}

	public ProductResponseDTO toProdutcResponseDTO(Product product) {
		ProductResponseDTO productResponseDTO = new ProductResponseDTO(null, product.getBarcode(),
				product.getDescription(), product.getPrice());
		return productResponseDTO;
	}
}
