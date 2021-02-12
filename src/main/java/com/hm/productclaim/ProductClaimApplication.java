package com.hm.productclaim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ProductClaimApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(ProductClaimApplication.class, args);
		log.info("ProductClaim started in {} environment",context.getEnvironment().getProperty("environment"));
		log.info("ProductClaim DB User is {} ",context.getEnvironment().getProperty("spring.datasource.username"));
	}
}
