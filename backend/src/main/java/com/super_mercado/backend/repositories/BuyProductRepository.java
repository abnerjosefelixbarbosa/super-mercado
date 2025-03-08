package com.super_mercado.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.super_mercado.backend.entities.BuyProduct;
import com.super_mercado.backend.entities.BuyProductId;

@Repository
public interface BuyProductRepository extends JpaRepository<BuyProduct, BuyProductId> {

}