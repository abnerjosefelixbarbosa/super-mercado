package com.super_mercado.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProdutcResponseDTO;

public interface ProductService {
	ProdutcResponseDTO registerProduct(ProductRequestDTO dto);
	ProdutcResponseDTO updateProductById(String id, ProductRequestDTO dto);
	Page<ProdutcResponseDTO> listProducts(Pageable pageable);
}