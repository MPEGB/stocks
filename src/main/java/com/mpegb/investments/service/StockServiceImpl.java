package com.mpegb.investments.service;

import com.mpegb.investments.model.Stock;
import com.mpegb.investments.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bhushan on 23/12/2017.
 */
@Service("stockService")
@Transactional
public class StockServiceImpl implements StockService{
    @Autowired
    private StockRepository stockRepository;

    public Stock findById(Long id) {
        return stockRepository.findOne(id);
    }

    public Stock findByName(String name) {
        return stockRepository.findByName(name);
    }

    @Override
    public void saveStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void updateStock(Stock stock) {
        saveStock(stock);
    }

    @Override
    public void deleteStockById(Long id) {
        stockRepository.delete(id);
    }

    @Override
    public void deleteAllStocks() {
        stockRepository.deleteAll();
    }

    @Override
    public List<Stock> findAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public boolean isStockExist(Stock stock) {
        return findByName(stock.getName())!=null;
    }
}
