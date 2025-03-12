package com.super_mercado.backend.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.entities.BuyProduct;
import com.super_mercado.backend.entities.BuyProductId;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.mappers.BuyMapper;
import com.super_mercado.backend.repositories.BuyRepository;
import com.super_mercado.backend.repositories.ProductRepository;
import com.super_mercado.backend.services.BuyService;
import com.super_mercado.backend.validations.BuyValidation;

import jakarta.transaction.Transactional;

@Service
public class BuyServiceImpl implements BuyService {
	@Autowired
	private BuyMapper buyMapper;
	@Autowired
	private BuyRepository buyRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BuyValidation buyValidation;

	@Transactional
	public BuyResponseDTO registerBuy(BuyRequestDTO dto) {
		Buy buy = buyMapper.toBuy(dto);
		buyValidation.validateBuy(buy);
		Double total = buy.getBuyProducts().stream()
		.mapToDouble((i) -> {
			Product productFound = productRepository.findByBarcode(i.getProduct().getBarcode()).get();
			return productFound.getPrice().doubleValue() * i.getAmount();
		})
		.sum();
		List<BuyProduct> buyProducts = new ArrayList<BuyProduct>();
		buy.getBuyProducts().stream()
		.forEach((i) -> {
			Product product = productRepository.findByBarcode(i.getProduct().getBarcode()).get();
			BuyProductId id = new BuyProductId(buy.getId(), product.getId());
			buy.setValue(BigDecimal.valueOf(total));
			BuyProduct buyProduct = new BuyProduct(id, buy, product, i.getAmount());
			buyProducts.add(buyProduct);
			buy.setBuyProducts(buyProducts);
			buyRepository.save(buy);
		});
		return buyMapper.toBuyResponseDTO(buy);
	}
}
