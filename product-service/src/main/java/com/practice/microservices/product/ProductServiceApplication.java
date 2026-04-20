package com.practice.microservices.product;

import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Product Service API",
				version = "1.0",
				description = "API documentation for Product Service"
		),
		externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
				description = "Product Service Wiki Documentation",
				url = "https://example.com/dummy-wiki-url"
		)
)
@Observed
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
}
