package com.super_mercado.backend.mappers.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProdutcResponseDTO;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.mappers.ProductMapper;

@Component
public class ProductMapperImpl implements ProductMapper {
	public Product toProduct(ProductRequestDTO dto) {
		Product product = new Product();
		product.setId(UUID.randomUUID().toString());
		BeanUtils.copyProperties(dto, product);
		return product;
	}
	
	public Product toProduct(String id, ProductRequestDTO dto) {
		Product product = new Product();
		product.setId(id);
		BeanUtils.copyProperties(dto, product);
		return product;
	}

	public ProdutcResponseDTO toProdutcResponseDTO(Product product) {
		ProdutcResponseDTO dto = new ProdutcResponseDTO();
		BeanUtils.copyProperties(product, dto);
		return dto;
	}
}
