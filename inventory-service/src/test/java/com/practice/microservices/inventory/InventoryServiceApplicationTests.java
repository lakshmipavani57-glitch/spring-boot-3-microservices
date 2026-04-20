package com.practice.microservices.inventory;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void checkInventory() {
		RestAssured.given()
				.accept("application/json")
				.queryParam("skuCode", "galaxy_24")
				.queryParam("quantity", "100")
				.when()
				.get("/api/inventory")
				.then()
				.statusCode(200)
				.body("$", Matchers.equalTo(true));

		RestAssured.given()
				.accept("application/json")
				.queryParam("skuCode", "iphone_15")
				.queryParam("quantity", "500")
				.when()
				.get("/api/inventory")
				.then()
				.statusCode(200)
				.body("$", Matchers.equalTo(false));
	}

}
