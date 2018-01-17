package com.glima.moneywise;

import com.glima.moneywise.config.property.MoneywiseApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MoneywiseApiProperty.class)
public class MoneywiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneywiseApplication.class, args);
	}
}
