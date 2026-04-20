package com.practice.microservices.inventory;

import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Inventory Service API",
				version = "1.0",
				description = "API documentation for Inventory Service"
		),
		externalDocs = @ExternalDocumentation(
				description = "Inventory Service Wiki Documentation",
				url = "https://example.com/dummy-wiki-url"
		)
)
@Observed
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
}