package com.super_mercado.backend.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buys_products")
public class BuyProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private BuyProductId buyProductId;
	@MapsId("buyId")
	@ManyToOne
	@JoinColumn(name = "buy_id")
	private Buy buy;
	@MapsId("productId")
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(nullable = false)
	private Integer amount;
}