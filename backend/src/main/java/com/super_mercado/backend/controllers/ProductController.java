package com.super_mercado.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProdutcResponseDTO;
import com.super_mercado.backend.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping(value = "/register-product")
	public ResponseEntity<ProdutcResponseDTO> registerProduct(@Valid @RequestBody ProductRequestDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.registerProduct(dto));
	}
	
	@PutMapping(value = "/update-product-by-id")
	public ResponseEntity<ProdutcResponseDTO> updateProductById(@RequestParam String id, @Valid @RequestBody ProductRequestDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.updateProductById(id, dto));
	}

	@GetMapping(value = "/list-products")
	public ResponseEntity<Page<ProdutcResponseDTO>> listProducts(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.listProducts(pageable));
	}
}