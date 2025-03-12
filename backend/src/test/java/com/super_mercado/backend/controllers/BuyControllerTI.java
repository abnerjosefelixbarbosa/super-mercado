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
import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
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
		ProductListDTO product1 = new ProductListDTO("1", null, null, 1);
		ProductListDTO product2 = new ProductListDTO("2", null, null, 1);
		BuyRequestDTO buyRequestDTO = new BuyRequestDTO(null, List.of(product1, product2));
		String json = objectMapper.writeValueAsString(buyRequestDTO);
		mockMvc.perform(post("/api/v1/buys/register-buy").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(print());
	}

	void load() {
		Product product1 = new Product(UUID.randomUUID().toString(), "1", "descrição1", BigDecimal.valueOf(9.90), null);
		Product product2 = new Product(UUID.randomUUID().toString(), "2", "descrição2", BigDecimal.valueOf(9.90), null);
		productRepository.save(product1);
		productRepository.save(product2);
	}
}
