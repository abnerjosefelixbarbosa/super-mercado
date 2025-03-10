package com.super_mercado.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.mappers.BuyMapper;
import com.super_mercado.backend.mappers.ProductMapper;
import com.super_mercado.backend.repositories.BuyProductRepository;
import com.super_mercado.backend.repositories.BuyRepository;
import com.super_mercado.backend.repositories.ProductRepository;
import com.super_mercado.backend.services.BuyService;
import com.super_mercado.backend.validations.BuyValidation;

import jakarta.transaction.Transactional;

@Service
public class BuyServiceImpl implements BuyService {
	@Autowired
	private BuyRepository buyRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BuyProductRepository buyProductRepository;
	@Autowired
	private BuyMapper buyMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private BuyValidation buyValidation;

	@Transactional
	public BuyResponseDTO registerBuy(BuyRequestDTO dto) {
		Buy buy = buyMapper.toBuy(dto);
		return null;
	}
}
