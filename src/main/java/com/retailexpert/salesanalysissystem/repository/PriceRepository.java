package com.retailexpert.salesanalysissystem.repository;

import com.retailexpert.salesanalysissystem.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
