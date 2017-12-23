package com.mpegb.investments.controller;



import com.mpegb.investments.model.Stock;
import com.mpegb.investments.service.StockService;
import com.mpegb.investments.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

import static com.mpegb.investments.util.CustomDateFormat.getFormat;

/**
 * Created by bhushan on 23/12/2017.
 */

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    StockService stockService; //Service to retrieve,update,delete and add stocks

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

    /**
     * Retrieve stock with id
     * @param id
     * @return ResponseEntity
     */

    @RequestMapping(value = "/stock/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStock(@PathVariable("id") long id) {
        logger.info("Fetching Stock with id {}", id);
        Stock stock = stockService.findById(id);
        if (stock == null) {
            logger.error("Stock with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Stock with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Stock>(stock, HttpStatus.OK);
    }

    /**
     * Create stock
     * @param stock
     * @param ucBuilder
     * @return ResponseEntity
     */

    @RequestMapping(value = "/stock/", method = RequestMethod.POST)
    public ResponseEntity<?> createStock(@RequestBody Stock stock, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Stock : {}", stock);

        if (stockService.isStockExist(stock)) {
            logger.error("Unable to create. A Stock with name {} already exist", stock.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Stock with name " +
                    stock.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        stock.setLastUpdate(LocalDateTime.now().format(getFormat()));
        stockService.saveStock(stock);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/stock/{id}").buildAndExpand(stock.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    /**
     * Update stock with id
     * @param id
     * @param stock
     * @return
     */

    @RequestMapping(value = "/stock/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStock(@PathVariable("id") long id, @RequestBody Stock stock) {
        logger.info("Updating Stock with id {}", id);

        Stock currentStock = stockService.findById(id);

        if (currentStock == null) {
            logger.error("Unable to update. Stock with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Stock with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentStock.setName(stock.getName());
        currentStock.setCurrentPrice(stock.getCurrentPrice());
        currentStock.setLastUpdate(LocalDateTime.now().format(getFormat()));

        stockService.updateStock(currentStock);
        return new ResponseEntity<Stock>(currentStock, HttpStatus.OK);
    }

    /**
     * Delete stock with id
     * @param id
     * @return ResponseEntity
     */

    @RequestMapping(value = "/stock/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStock(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Stock with id {}", id);

        Stock stock = stockService.findById(id);
        if (stock == null) {
            logger.error("Unable to delete. Stock with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Stock with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        stockService.deleteStockById(id);
        return new ResponseEntity<Stock>(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete all stocks
     * @return ResponseEntity
     */

    @RequestMapping(value = "/stocks/", method = RequestMethod.DELETE)
    public ResponseEntity<Stock> deleteAllStocks() {
        logger.info("Deleting All Stocks");

        stockService.deleteAllStocks();
        return new ResponseEntity<Stock>(HttpStatus.NO_CONTENT);
    }


}
