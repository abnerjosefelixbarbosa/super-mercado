package com.super_mercado.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.super_mercado.backend.entities.Buy;

@Repository
public interface BuyRepository extends JpaRepository<Buy, String> {

}