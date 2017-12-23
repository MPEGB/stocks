package com.mpegb.investments.service;

import com.mpegb.investments.model.Stock;

import java.util.List;

/**
 * Created by bhushan on 23/12/2017.
 */
public interface StockService {

    Stock findById(Long id);

    Stock findByName(String name);

    void saveStock(Stock stock);

    void updateStock(Stock stock);

    void deleteStockById(Long id);

    void deleteAllStocks();

    List<Stock> findAllStocks();

    boolean isStockExist(Stock stock);
}
