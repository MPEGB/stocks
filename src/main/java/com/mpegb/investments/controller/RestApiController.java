package com.mpegb.investments.controller;

/**
 * Created by bhushan on 23/12/2017.
 */

import com.mpegb.investments.model.Stock;
import com.mpegb.investments.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    StockService stockService; //Service to do retrieve,update,delete and add stocks

    /**
     * Retrieve all stocks
     * @return ResponseEntity
     */

    @RequestMapping(value = "/stocks/", method = RequestMethod.GET)
    public ResponseEntity<List<Stock>> listAllStocks() {
        List<Stock> stocks = stockService.findAllStocks();
        if (stocks.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
    }



}
