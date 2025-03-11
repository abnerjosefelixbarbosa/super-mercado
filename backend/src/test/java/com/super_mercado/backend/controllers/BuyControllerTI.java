package com.super_mercado.backend.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.super_mercado.backend.dtos.lists.ProductListDTO;
import com.super_mercado.backend.dtos.requests.BuyProductRequestDTO;
import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.repositories.BuyRepository;
import com.super_mercado.backend.repositories.ProductRepository;

@SpringBootTest
@ActiveProfiles(value = "dev")
@AutoConfigureMockMvc
class BuyControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private BuyRepository buyRepository;
	@Autowired
	private ProductRepository productRepository;

	@BeforeEach
	void setUp() throws Exception {
		buyRepository.deleteAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		buyRepository.deleteAll();
	}

	@Test
	void sholdRegisterBuyAndReturn201() throws Exception {
		load();
		
		ProductRequestDTO productRequestDTO1 = new ProductRequestDTO();
		productRequestDTO1.setBarcode("1");
		ProductRequestDTO productRequestDTO2 = new ProductRequestDTO();
		productRequestDTO2.setBarcode("2");
		
		BuyProductRequestDTO buyProductRequestDTO1 = new BuyProductRequestDTO();
		buyProductRequestDTO1.setProductRequestDTO(productRequestDTO1);
		buyProductRequestDTO1.setAmount(1);
		BuyProductRequestDTO buyProductRequestDTO2 = new BuyProductRequestDTO();
		buyProductRequestDTO2.setProductRequestDTO(productRequestDTO2);
		buyProductRequestDTO2.setAmount(1);
		
		/*
		ProductListDTO productListDTO1 = new ProductListDTO();
		productListDTO1.setBarcode("1");
		productListDTO1.setAmount(1);
		ProductListDTO productListDTO2 = new ProductListDTO();
		productListDTO2.setBarcode("2");
		productListDTO2.setAmount(1);
		*/
		
		BuyRequestDTO buyRequestDTO = new BuyRequestDTO();
		buyRequestDTO.setBuyProductRequestDTOs(List.of(buyProductRequestDTO1, buyProductRequestDTO2));
		//buyRequestDTO.setProductListDTOs(List.of(productListDTO1, productListDTO2));
		
		String json = objectMapper.writeValueAsString(buyRequestDTO);
		
		mockMvc.perform(post("/api/v1/buys/register-buy").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(print());
	}
	
	void load() {
		Product product1 = new Product();
		product1.setId(UUID.randomUUID().toString());
		product1.setBarcode("1");
		product1.setDescription("descrição1");
		product1.setPrice(BigDecimal.valueOf(9.90));
		
		Product product2 = new Product();
		product2.setId(UUID.randomUUID().toString());
		product2.setBarcode("2");
		product2.setDescription("descrição2");
		product2.setPrice(BigDecimal.valueOf(9.90));
		
		productRepository.save(product1);
		productRepository.save(product2);
	}
}
