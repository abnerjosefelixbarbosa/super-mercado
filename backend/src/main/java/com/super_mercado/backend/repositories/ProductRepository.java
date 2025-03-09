package com.super_mercado.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.super_mercado.backend.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	boolean existsByBarcode(String barcode);
	Optional<Product> findByBarcode(String barcode);
}