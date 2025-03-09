package com.super_mercado.backend.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.dtos.responses.ProductResponseDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.entities.BuyProduct;
import com.super_mercado.backend.entities.BuyProductId;
import com.super_mercado.backend.entities.Product;
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
		buyValidation.validateProduct(buy, dto.getDtos());
		AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);
		List<ProductResponseDTO> produtcResponseDTOs = new ArrayList<ProductResponseDTO>();
		dto.getDtos().forEach((i) -> {
			Product productFound = productRepository.findByBarcode(i.getBarcode()).get();
			total.set(total.get().add(productFound.getPrice()));
			produtcResponseDTOs.add(productMapper.toProdutcResponseDTO(productFound));
		});
		buy.setValue(total.get());
		Buy buySaved = buyRepository.save(buy);
		String buyId = buy.getId();
		BuyProductId buyProductId = new BuyProductId();
		BuyProduct buyProduct = new BuyProduct();
		dto.getDtos().forEach((i) -> {
			Product productFound = productRepository.findByBarcode(i.getBarcode()).get();
			String productId = productFound.getId();
			buyProductId.setBuyId(buyId);
			buyProductId.setProductId(productId);
			buyProduct.setProduct(productFound);
			buyProduct.setBuy(buySaved);
			buyProduct.setAmount(1);
			buyProductRepository.save(buyProduct);
		});
		BuyResponseDTO buyResponseDTO = buyMapper.toBuyResponseDTO(buy, produtcResponseDTOs);
		return buyResponseDTO;
	}
}
