package com.fpd.miniwms.repository;

import com.fpd.miniwms.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
