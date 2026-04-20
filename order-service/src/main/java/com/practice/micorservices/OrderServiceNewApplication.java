package com.practice.micorservices;

import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Order Service API",
				version = "1.0",
				description = "API documentation for Order Service"
		),
		externalDocs = @ExternalDocumentation(
				description = "Order Service Wiki Documentation",
				url = "https://example.com/dummy-wiki-url"
		)
)
@Observed
public class OrderServiceNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceNewApplication.class, args);
	}

}
