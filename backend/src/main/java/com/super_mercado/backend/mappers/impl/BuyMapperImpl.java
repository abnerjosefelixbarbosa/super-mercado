package com.super_mercado.backend.mappers.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyProductResponseDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.mappers.BuyMapper;
import com.super_mercado.backend.mappers.BuyProductMapper;

@Component
public class BuyMapperImpl implements BuyMapper {
	@Autowired
	private BuyProductMapper buyProductMapper;

	public Buy toBuy(BuyRequestDTO dto) {
		Buy buy = new Buy();
		BeanUtils.copyProperties(dto, buy);
		buy.setId(UUID.randomUUID().toString());
		buy.setDate(LocalDate.now());
		buy.setTime(LocalTime.now());
		return buy;
	}

	public BuyResponseDTO toBuyResponseDTO(Buy buy) {
		BuyResponseDTO dto = new BuyResponseDTO();
		BeanUtils.copyProperties(buy, dto);
		List<BuyProductResponseDTO> buyProductResponseDTOs = buy.getBuyProducts().stream()
				.map(buyProductMapper::toBuyProductResponseDTO).collect(Collectors.toList());
		dto.setBuyProductResponseDTOs(buyProductResponseDTOs);
		return dto;
	}
}
