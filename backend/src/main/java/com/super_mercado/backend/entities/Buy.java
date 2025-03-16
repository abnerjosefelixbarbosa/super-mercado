package com.super_mercado.backend.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buys")
public class Buy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Column(name = "buy_date", length = 20, nullable = true)
	private String date;
	@Column(name = "buy_time", length = 20, nullable = true)
	private String time;
	@Column(length = 20)
	private String customerDocument;
	@Column(name = "buy_value", nullable = true, precision = 10, scale = 2)
	private BigDecimal value;
	@OneToMany(mappedBy = "buy", cascade = CascadeType.ALL)
	private List<Item> buyProducts;
}