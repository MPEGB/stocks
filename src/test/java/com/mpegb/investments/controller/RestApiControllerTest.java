package com.mpegb.investments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpegb.investments.configuration.JpaConfiguration;
import com.mpegb.investments.controller.RestApiController;
import com.mpegb.investments.model.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bhushan on 23/12/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { JpaConfiguration.class },loader = AnnotationConfigContextLoader.class)
@WebMvcTest(RestApiController.class)
public class RestApiControllerTest {
    private MockMvc mockMvc;

    UriComponentsBuilder uriComponentsBuilder;

    Stock testStock;

    @MockBean
    private RestApiController restApiController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(restApiController)
                .build();
        uriComponentsBuilder = UriComponentsBuilder.newInstance();
        testStock = new Stock();
        testStock.setName("TEST");
        testStock.setId(1L);
        testStock.setCurrentPrice(10.0);
    }

    @Test
    public void listAllStocks()
            throws Exception {
        List<Stock> allStocks = Arrays.asList(testStock);
        ResponseEntity<List<Stock>> testAllStocks = new ResponseEntity<List<Stock>>(allStocks, HttpStatus.OK);
        given(restApiController.listAllStocks()).willReturn(testAllStocks);

        mockMvc.perform(get("/api/stock/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(testStock.getName())));
    }

    @Test
    public void getStockById() throws Exception{
        ResponseEntity resultStock = new ResponseEntity<Stock>(testStock,HttpStatus.OK);
        given(restApiController.getStock(testStock.getId())).willReturn(resultStock);

        mockMvc.perform(get("/api/stock/" + testStock.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(testStock.getName())));
    }

    @Test
    public void postStock() throws Exception{
        ResponseEntity resultStock = new ResponseEntity<String>(new HttpHeaders(),HttpStatus.OK);
        given(restApiController.createStock(testStock,uriComponentsBuilder)).willReturn(resultStock);

        mockMvc.perform(post("/api/stock/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(resultStock)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStock() throws Exception {
        ResponseEntity resultStock = new ResponseEntity<Stock>(new HttpHeaders(), HttpStatus.OK);
        given(restApiController.updateStock(1L, testStock)).willReturn(resultStock);

        mockMvc.perform(put("/api/stock/" + testStock.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(resultStock)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStock() throws Exception{
        ResponseEntity resultStock = new ResponseEntity<String>(new HttpHeaders(),HttpStatus.OK);
        given(restApiController.deleteStock(1L)).willReturn(resultStock);

        mockMvc.perform(delete("/api/stock/"+testStock.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(resultStock)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllStock() throws Exception{
        ResponseEntity resultStock = new ResponseEntity<Stock>(new HttpHeaders(),HttpStatus.OK);
        given(restApiController.deleteAllStocks()).willReturn(resultStock);

        mockMvc.perform(delete("/api/stock/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testStock)))
                .andExpect(status().isOk());
    }

    /*
    * converts a Java object into JSON representation
    */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}