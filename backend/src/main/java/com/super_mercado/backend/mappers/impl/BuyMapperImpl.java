package com.super_mercado.backend.mappers.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyProductResponseDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.dtos.responses.ProductResponseDTO;
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
		List<BuyProduct> butProducts = new ArrayList<BuyProduct>();
		dto.getBuyProductRequestDTOs().forEach((i) -> {
			Product product = new Product();
			BeanUtils.copyProperties(i.getProductRequestDTO(), product);
			BuyProduct buyProduct = new BuyProduct();
			buyProduct.setProduct(product);
			buyProduct.setAmount(i.getAmount());
			butProducts.add(buyProduct);
		});
		buy.setBuyProducts(butProducts);
		return buy;
	}

	public BuyResponseDTO toBuyResponseDTO(Buy buy) {
		BuyResponseDTO buyResponseDTO = new BuyResponseDTO();
		BeanUtils.copyProperties(buy, buyResponseDTO);
		List<BuyProductResponseDTO> buyProductResponseDTOs = new ArrayList<BuyProductResponseDTO>();
		buy.getBuyProducts().forEach((i) -> {
			ProductResponseDTO productResponseDTO = new ProductResponseDTO();
			BeanUtils.copyProperties(i.getProduct(), productResponseDTO);
			BuyProductResponseDTO buyProductResponseDTO = new BuyProductResponseDTO();
			buyProductResponseDTO.setProductResponseDTO(productResponseDTO);
			buyProductResponseDTOs.add(buyProductResponseDTO);
		});
		buyResponseDTO.setBuyProductResponseDTOs(buyProductResponseDTOs);
		return buyResponseDTO;
	}
}
