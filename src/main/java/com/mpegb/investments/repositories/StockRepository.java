package com.mpegb.investments.repositories;

import com.mpegb.investments.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bhushan on 23/12/2017.
 */
@Repository
public interface StockRepository  extends JpaRepository<Stock, Long> {
    Stock findByName(String name);
}
