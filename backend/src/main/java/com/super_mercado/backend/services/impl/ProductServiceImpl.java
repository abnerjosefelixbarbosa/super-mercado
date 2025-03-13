package com.super_mercado.backend.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.dtos.responses.ProductResponseDTO;
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
	public ProductResponseDTO registerProduct(ProductRequestDTO dto) {
		Product product = productMapper.toProduct(dto);
		productValidation.validateProduct(product);
		Product productSaved = productRepository.save(product);
		ProductResponseDTO produtcResponseDTO = productMapper.toProdutcResponseDTO(productSaved);
		return produtcResponseDTO;
	}

	@Transactional
	public ProductResponseDTO updateProductById(String id, ProductRequestDTO dto) {
		Product product = productMapper.toProduct(id, dto);
		productValidation.validateProduct(id, product);
		Product productFound = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ID não encontrado."));
		BeanUtils.copyProperties(product, productFound);
		Product productSaved = productRepository.save(productFound);
		ProductResponseDTO produtcResponseDTO = productMapper.toProdutcResponseDTO(productSaved);
		return produtcResponseDTO;
	}

	public Page<ProductResponseDTO> listProducts(Pageable pageable) {
		Page<Product> page = productRepository.findAll(pageable);
		return page.map(productMapper::toProdutcResponseDTO);
	}

	public ProductResponseDTO searchProductByBarcode(String barcode) {
		Product product = productRepository.findByBarcode(barcode)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
		ProductResponseDTO productResponseDTO = productMapper.toProdutcResponseDTO(product);
		return productResponseDTO;
	}
}
