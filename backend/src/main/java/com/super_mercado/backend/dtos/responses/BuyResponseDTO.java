package com.super_mercado.backend.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor	
public class BuyResponseDTO {
	private String id;
	private LocalDate date;
	private LocalTime time;
	private String customerDocument;
	private BigDecimal value;
	private List<ProductResponseDTO> productResponseDTOs; 
}