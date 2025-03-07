package com.super_mercado.backend.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BuyProductId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String buyId;
	private String productId;
}
