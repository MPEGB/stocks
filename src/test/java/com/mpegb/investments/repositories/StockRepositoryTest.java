package com.mpegb.investments.repositories;

import com.mpegb.investments.configuration.JpaConfiguration;
import com.mpegb.investments.model.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by bhushan on 23/12/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { JpaConfiguration.class },loader = AnnotationConfigContextLoader.class)
@Transactional
public class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;


    @Test
    public void findByName() throws Exception {
        Stock stock = new Stock();
        stock.setName("TEST");
        stock.setCurrentPrice(new Double(10));
        stock.setLastUpdate(LocalDateTime.now().toString());
        stockRepository.save(stock);
        Stock foundStock = stockRepository.findByName("TEST");
        assertEquals(foundStock.getName(), stock.getName());
    }

}
