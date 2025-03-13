package com.super_mercado.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.super_mercado.backend.entities.BuyProductId;
import com.super_mercado.backend.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, BuyProductId> {
}