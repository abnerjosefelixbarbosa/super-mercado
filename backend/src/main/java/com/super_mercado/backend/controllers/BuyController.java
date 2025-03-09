package com.super_mercado.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.super_mercado.backend.dtos.requests.BuyRequestDTO;
import com.super_mercado.backend.dtos.responses.BuyResponseDTO;
import com.super_mercado.backend.services.BuyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/buys")
public class BuyController {
	@Autowired
	private BuyService buyService;
	
	@PostMapping(value = "/register-buy")
	public ResponseEntity<BuyResponseDTO> registerBuy(@Valid @RequestBody BuyRequestDTO dto) {
		BuyResponseDTO buyResponseDTO = buyService.registerBuy(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(buyResponseDTO);
	}
}