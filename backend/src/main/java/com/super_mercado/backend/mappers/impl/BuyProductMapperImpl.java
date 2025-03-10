package com.super_mercado.backend.mappers.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.responses.BuyProductResponseDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.dtos.responses.ProductResponseDTO;
import com.super_mercado.backend.entities.BuyProduct;
import com.super_mercado.backend.mappers.BuyProductMapper;

@Component
public class BuyProductMapperImpl implements BuyProductMapper {
	@Autowired
	private ProductResponseDTO productResponseDTO;
	@Autowired
	private BuyResponseDTO buyResponseDTO;
	
	public BuyProductResponseDTO toBuyProductResponseDTO(BuyProduct buyProduct) {
		BuyProductResponseDTO dto = new BuyProductResponseDTO();
		dto.setBuyResponseDTO(buyResponseDTO);
		dto.setProductResponseDTO(productResponseDTO);
		BeanUtils.copyProperties(buyProduct, dto);
		return dto;
	}
}