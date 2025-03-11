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
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.dtos.responses.ProductResponseDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.mappers.BuyMapper;

@Component
public class BuyMapperImpl implements BuyMapper {
	public Buy toBuy(BuyRequestDTO dto) {
		Buy buy = new Buy();
		buy.setId(UUID.randomUUID().toString());
		buy.setCustomerDocument(dto.getCustomerDocment());
		buy.setDate(LocalDate.now());
		buy.setTime(LocalTime.now());
		buy.setValue(BigDecimal.ZERO);
		
		return buy;
	}

	public BuyResponseDTO toBuyResponseDTO(Buy buy) {
		BuyResponseDTO buyResponseDTO = new BuyResponseDTO();
		BeanUtils.copyProperties(buy, buyResponseDTO);
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		
		List<ProductResponseDTO> productResponseDTOs = new ArrayList<ProductResponseDTO>();
		buyResponseDTO.setProductResponseDTOs(productResponseDTOs);
		
		return buyResponseDTO;
	}
}
