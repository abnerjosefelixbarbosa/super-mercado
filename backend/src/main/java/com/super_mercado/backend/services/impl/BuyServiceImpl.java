package com.super_mercado.backend.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.entities.Item;
import com.super_mercado.backend.entities.ItemId;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.mappers.BuyMapper;
import com.super_mercado.backend.repositories.BuyRepository;
import com.super_mercado.backend.services.BuyService;
import com.super_mercado.backend.services.ProductService;
import com.super_mercado.backend.validations.BuyValidation;

import jakarta.transaction.Transactional;

@Service
public class BuyServiceImpl implements BuyService {
	@Autowired
	private BuyMapper buyMapper;
	@Autowired
	private BuyRepository buyRepository;
	@Autowired
	private ProductService productService;
    @Autowired
	private BuyValidation buyValidation;

	@Transactional
	public BuyResponseDTO registerBuy(BuyRequestDTO dto) {
		Buy buy = buyMapper.toBuy(dto);
		buyValidation.validateBuy(buy);
		Double total = buy.getBuyProducts().stream()
		.mapToDouble((i) -> {
			Product productFound = productService.findByBarcode(i.getProduct().getBarcode());
			return productFound.getPrice().doubleValue() * i.getAmount();
		})
		.sum();
		List<Item> buyProducts = new ArrayList<Item>();
		buy.getBuyProducts().stream()
		.forEach((i) -> {
			Product product = productService.findByBarcode(i.getProduct().getBarcode());
			ItemId id = new ItemId(buy.getId(), product.getId());
			buy.setValue(BigDecimal.valueOf(total));
			Item buyProduct = new Item(id, buy, product, i.getAmount());
			buyProducts.add(buyProduct);
			buy.setBuyProducts(buyProducts);
			buyRepository.save(buy);
		});
		return buyMapper.toBuyResponseDTO(buy);
	}
}
