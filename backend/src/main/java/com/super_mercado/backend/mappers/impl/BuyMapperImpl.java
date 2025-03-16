package com.super_mercado.backend.mappers.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.requests.ProductItemRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.entities.Buy;
import com.super_mercado.backend.entities.Item;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.mappers.BuyMapper;

@Component
public class BuyMapperImpl implements BuyMapper {
	public Buy toBuy(BuyRequestDTO dto) {
		List<Item> items = dto.getProductItemRequestDTOs().stream().map((i) -> {
			Product product = new Product(null, i.getBarcode(), null, null, null);
			Item item = new Item(null, null, product, i.getAmount());
			return item;
		}).toList();
		LocalTime time = LocalTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String timeFormatter = time.format(dateTimeFormatter);
		//time = LocalTime.parse(timeFormatter);
		Buy buy = new Buy(UUID.randomUUID().toString(), LocalDate.now(), LocalTime.parse(timeFormatter), dto.getCustomerDocment(),
				BigDecimal.ZERO, items);
		return buy;
	}

	public BuyResponseDTO toBuyResponseDTO(Buy buy) {
		List<ProductItemRequestDTO> productListDTOs = buy.getBuyProducts().stream().map((i) -> {
			ProductItemRequestDTO productItemRequestDTO = new ProductItemRequestDTO(i.getProduct().getBarcode(),
					i.getProduct().getDescription(), i.getProduct().getPrice(), i.getAmount());
			return productItemRequestDTO;
		}).toList();
		BuyResponseDTO buyResponseDTO = new BuyResponseDTO(buy.getId(), buy.getDate(), buy.getTime(),
				buy.getCustomerDocument(), buy.getValue(), productListDTOs);
		return buyResponseDTO;
	}
}
