package com.super_mercado.backend.mappers.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.lists.ProductListDTO;
import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.entities.BuyProduct;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.mappers.BuyMapper;

@Component
public class BuyMapperImpl implements BuyMapper {
	public Buy toBuy(BuyRequestDTO dto) {
		List<BuyProduct> buyProducts = dto.getProductListDTOs().stream().map((i) -> {
			Product product = new Product(null, i.getBarcode(), null, null, null);
			BuyProduct buyProduct = new BuyProduct(null, null, product, i.getAmount());
			return buyProduct;
		}).toList();
		Buy buy = new Buy(UUID.randomUUID().toString(), LocalDate.now(), LocalTime.now(), dto.getCustomerDocment(),
				BigDecimal.ZERO, buyProducts);
		return buy;
	}

	public BuyResponseDTO toBuyResponseDTO(Buy buy) {
		List<ProductListDTO> productListDTOs = buy.getBuyProducts().stream().map((i) -> {
			ProductListDTO productListDTO = new ProductListDTO(i.getProduct().getBarcode(),
					i.getProduct().getDescription(), i.getProduct().getPrice(), i.getAmount());
			return productListDTO;
		}).toList();
		BuyResponseDTO buyResponseDTO = new BuyResponseDTO(buy.getId(), buy.getDate(), buy.getTime(),
				buy.getCustomerDocument(), buy.getValue(), productListDTOs);
		return buyResponseDTO;
	}
}
