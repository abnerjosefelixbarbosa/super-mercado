package com.super_mercado.backend.mappers.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyProductResponseDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.entities.BuyProduct;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.mappers.BuyMapper;

@Component
public class BuyMapperImpl implements BuyMapper {
	public Buy toBuy(BuyRequestDTO dto) {
		Buy buy = new Buy();
		BeanUtils.copyProperties(dto, buy);
		buy.setId(UUID.randomUUID().toString());
		buy.setDate(LocalDate.now());
		buy.setTime(LocalTime.now());
		buy.setValue(BigDecimal.ZERO);
		
		List<BuyProduct> buyProducts = dto.getBuyProductRequestDTOs().stream().map((i) -> {
			BuyProduct buyProduct = new BuyProduct();
			buyProduct.setAmount(i.getAmount());
			
			Product product = new Product();
			
			product.setBarcode(i.getProductRequestDTO().getBarcode());
	        
			buyProduct.setProduct(product);
			
			return buyProduct;
		}).collect(Collectors.toList());
		
		buy.setBuyProducts(buyProducts);
		
		return buy;
	}

	public BuyResponseDTO toBuyResponseDTO(Buy buy) {
		BuyResponseDTO buyResponseDTO = new BuyResponseDTO();
		BeanUtils.copyProperties(buy, buyResponseDTO);
		
		List<BuyProductResponseDTO> buyProductResponseDTOs = buy.getBuyProducts().stream().map((i) -> {
			BuyProductResponseDTO buyProductResponseDTO = new BuyProductResponseDTO();
			BeanUtils.copyProperties(i, buyResponseDTO);
			
			return buyProductResponseDTO;
		}).toList();
		
		buyResponseDTO.setBuyProductResponseDTOs(buyProductResponseDTOs);
		
		return buyResponseDTO;
	}
}
