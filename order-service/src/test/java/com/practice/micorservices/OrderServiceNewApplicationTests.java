package com.practice.micorservices;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;

import static com.practice.micorservices.stubs.InventoryStubs.getMockServer;
import static com.practice.micorservices.stubs.InventoryStubs.stubInventoryCall;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = "spring.profiles.active=it")
@AutoConfigureWireMock(port = 0)
class OrderServiceNewApplicationTests {

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldPlaceOrder() {

		stubInventoryCall("pixel_8", 1);

		RestAssured.given()
				.contentType("application/json")
				.body("""
						{
						    "skuCode": "pixel_8",
						    "price": "1500",
						    "quantity": "1"
						}
						""")
				.when()
				.post("/api/orders")
				.then()
				.statusCode(201);
	}
}
