package com.super_mercado.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProductResponseDTO;
import com.super_mercado.backend.entities.Product;

public interface ProductService {
	ProductResponseDTO registerProduct(ProductRequestDTO dto);
	ProductResponseDTO updateProductById(String id, ProductRequestDTO dto);
	Page<ProductResponseDTO> listProducts(Pageable pageable);
	ProductResponseDTO searchProductByBarcode(String barcode);
	Product findByBarcode(String barcode);
	boolean existsByBarcode(String barcode);
}