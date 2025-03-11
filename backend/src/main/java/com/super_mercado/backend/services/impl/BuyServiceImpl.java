package com.super_mercado.backend.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.super_mercado.backend.dtos.lists.ProductListDTO;
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

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BuyServiceImpl implements BuyService {
	@Autowired
	private BuyMapper buyMapper;
	@Autowired
	private BuyRepository buyRepository;
	@Autowired
	private ProductRepository productRepository;

	@Transactional
	public BuyResponseDTO registerBuy(BuyRequestDTO dto) {
		Buy buy = buyMapper.toBuy(dto);
		
		Double total = dto.getProductListDTOs().stream()
		.mapToDouble((product) -> {
			Product productFound = productRepository.findByBarcode(product.getBarcode()).get();
			
			return productFound.getPrice().doubleValue() * product.getAmount();
		})
		.sum();
		
		dto.getProductListDTOs().stream()
		.forEach((product) -> {
			Product productFound = productRepository.findByBarcode(product.getBarcode()).get();
			
			BuyProductId id = new BuyProductId(buy.getId(), productFound.getId());
			
			BuyProduct buyProduct = new BuyProduct();
			buyProduct.setId(id);
			buyProduct.setBuy(buy);
			buyProduct.setAmount(product.getAmount());
			buyProduct.setProduct(productFound);
			
			List<BuyProduct> buyProducts = new ArrayList<BuyProduct>();
			buyProducts.add(buyProduct);
			
			buy.setBuyProducts(buyProducts);
			buy.setValue(BigDecimal.valueOf(total));
			
			buyRepository.save(buy);
		}); 
		
		return buyMapper.toBuyResponseDTO(buy);
	}
}
