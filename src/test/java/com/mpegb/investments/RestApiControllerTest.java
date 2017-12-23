package com.mpegb.investments;

import com.mpegb.investments.configuration.JpaConfiguration;
import com.mpegb.investments.controller.RestApiController;
import com.mpegb.investments.model.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void init(){
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

        mockMvc.perform(get("/api/stocks/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(testStock.getName())));
    }

}
