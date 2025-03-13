package com.super_mercado.backend.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.super_mercado.backend.dtos.requests.ProductItemRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor	
public class BuyResponseDTO {
	private String id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime time;
	private String customerDocument;
	private BigDecimal value;
	private List<ProductItemRequestDTO> productListDTOs; 
}