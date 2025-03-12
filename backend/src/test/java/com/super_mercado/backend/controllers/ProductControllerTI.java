package com.super_mercado.backend.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
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
import com.super_mercado.backend.dtos.requests.ProductRequestDTO;
import com.super_mercado.backend.entities.Product;
import com.super_mercado.backend.repositories.ProductRepository;

@SpringBootTest
@ActiveProfiles(value = "dev")
@AutoConfigureMockMvc
class ProductControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;
	private String id;

	@BeforeEach
	void setUp() throws Exception {
		productRepository.deleteAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		productRepository.deleteAll();
	}

	@Test
	void sholdRegisterProductAndReturn201() throws Exception {
		load();
		ProductRequestDTO productRequestDTO = new ProductRequestDTO("2", "descrição1", new BigDecimal("9.90"));
		String json = objectMapper.writeValueAsString(productRequestDTO);
		mockMvc.perform(post("/api/v1/products/register-product").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(print());
	}

	@Test
	void sholdUpdateProductByIdAndReturn200() throws Exception {
		load();
		ProductRequestDTO productRequestDTO = new ProductRequestDTO("2", "descrição1", new BigDecimal("9.90"));
		String json = objectMapper.writeValueAsString(productRequestDTO);
		mockMvc.perform(put("/api/v1/products/update-product-by-id").queryParam("id", id)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}

	@Test
	void sholdListProductsAndReturn200() throws Exception {
		load();
		mockMvc.perform(get("/api/v1/products/list-products")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}

	void load() {
		Product product1 = new Product(UUID.randomUUID().toString(), "1", "descrição1", new BigDecimal("9.90"), null);
		productRepository.save(product1);
		id = product1.getId();
	}
}
