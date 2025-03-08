package com.super_mercado.backend.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProdutcResponseDTO;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.mappers.ProductMapper;
import com.super_mercado.backend.repositories.ProductRepository;
import com.super_mercado.backend.services.ProductService;
import com.super_mercado.backend.validations.ProductValidation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductValidation productValidation;

	@Transactional
	public ProdutcResponseDTO registerProduct(ProductRequestDTO dto) {
		Product product = productMapper.toProdut(dto);
		productValidation.validateProduct(product);
		Product productSaved = productRepository.save(product);
		return productMapper.toProdutcResponseDTO(productSaved);
	}

	@Transactional
	public ProdutcResponseDTO updateProductById(String id, ProductRequestDTO dto) {
		Product product = productMapper.toProdut(dto);
		productValidation.validateProduct(product);
		Product productFound = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ID não encontrado."));
		BeanUtils.copyProperties(product, productFound);
		productFound.setId(id);
		Product productSaved = productRepository.save(productFound);
		return productMapper.toProdutcResponseDTO(productSaved);
	}

	public Page<ProdutcResponseDTO> listProducts(Pageable pageable) {
		return productRepository.findAll(pageable).map(productMapper::toProdutcResponseDTO);
	}
}
