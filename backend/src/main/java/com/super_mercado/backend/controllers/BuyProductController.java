package com.super_mercado.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/buy-product")
public class BuyProductController {
	/*
	@PostMapping(value = "/register-buy-product")
	public ResponseEntity<BuyResponseDTO> registerBuyProduct(@Valid @RequestBody BuyRequestDTO dto) {
		//BuyResponseDTO buyResponseDTO = buyService.registerBuy(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(buyResponseDTO);
	}
	*/
}