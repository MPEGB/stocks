package com.mpegb.investments;

import com.mpegb.investments.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by bhushan on 23/12/2017.
 */
@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.mpegb.investments"})
public class StocksApp {
    public static void main(String[] args) {
        SpringApplication.run(StocksApp.class, args);
    }
}
