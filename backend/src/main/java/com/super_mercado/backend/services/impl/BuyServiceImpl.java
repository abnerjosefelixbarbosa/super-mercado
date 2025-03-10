package com.super_mercado.backend.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
	private BuyValidation buyValidation;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BuyRepository buyRepository;

	@Transactional
	public BuyResponseDTO registerBuy(BuyRequestDTO dto) {
		Buy buy = buyMapper.toBuy(dto);
		buyValidation.validateProduct(buy);
		Double total = buy.getBuyProducts().stream().mapToDouble((i) -> {
			String barcode = i.getProduct().getBarcode();
			Product product = productRepository.findByBarcode(barcode).get();
			return product.getPrice().doubleValue() * i.getAmount();
		}).sum();
		buy.setValue(BigDecimal.valueOf(total));
		List<BuyProduct> buyProducts = buy.getBuyProducts().stream().map((i) -> {
			Product product = productRepository.findByBarcode(i.getProduct().getBarcode()).orElse(null);
			BuyProductId buyProductId = new BuyProductId();
			buyProductId.setBuyId(buy.getId());
			buyProductId.setProductId(product.getId());
			BuyProduct buyProduct = new BuyProduct();
			buyProduct.setBuy(buy);
			buyProduct.setProduct(product);
			buyProduct.setBuyProductId(buyProductId);
			return buyProduct;
		}).collect(Collectors.toList());
		buy.setBuyProducts(buyProducts);
	    buyRepository.save(buy);
		return buyMapper.toBuyResponseDTO(buy);
	}
}
