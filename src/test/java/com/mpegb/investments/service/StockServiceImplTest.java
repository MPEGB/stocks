package com.mpegb.investments.service;

import com.mpegb.investments.model.Stock;
import com.mpegb.investments.repositories.StockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by bhushan on 23/12/2017.
 */
@RunWith(SpringRunner.class)
public class StockServiceImplTest {
    @TestConfiguration
    static class StockServiceImplTestContextConfiguration {

        @Bean
        public StockService stockService() {
            return new StockServiceImpl();
        }
    }

    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;

    @Before
    public void setUp() {
        Stock testStock = new Stock();
        testStock.setName("TEST");

        Mockito.when(stockRepository.findByName(testStock.getName()))
                .thenReturn(testStock);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "TEST";
        Stock foundStock = stockService.findByName("TEST");

        assertEquals(foundStock.getName(),name);
    }
}
