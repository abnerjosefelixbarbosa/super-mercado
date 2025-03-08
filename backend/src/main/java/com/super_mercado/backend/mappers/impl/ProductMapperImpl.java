package com.super_mercado.backend.mappers.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProdutcResponseDTO;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.mappers.ProductMapper;

@Component
public class ProductMapperImpl implements ProductMapper {
	public Product toProdut(ProductRequestDTO dto) {
		Product product = new Product();
		BeanUtils.copyProperties(dto, product);
		return product;
	}

	public ProdutcResponseDTO toProdutcResponseDTO(Product product) {
		ProdutcResponseDTO dto = new ProdutcResponseDTO();
		BeanUtils.copyProperties(product, dto);
		return dto;
	}
}
