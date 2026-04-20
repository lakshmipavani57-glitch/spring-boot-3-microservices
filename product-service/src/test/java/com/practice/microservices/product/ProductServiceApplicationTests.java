package com.practice.microservices.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
properties = "spring.profiles.active=it")
@Import(TestContainerConfiguration.class)
class ProductServiceApplicationTests {

	/*@Container
	@ServiceConnection
	static MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:6.0.5");*/
	//no need to give here as we already have in TestContainerConfiguration

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;

		RestAssured.given()
				.contentType("application/json")
				.body("""
                    {
                      "name": "Seed Product",
                      "description": "Seeded",
                      "price": 100.0
                    }
                    """)
				.post("/api/products");
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
				    "name": "iPhone 13",
				    "description": "Latest model of iPhone",
				    "price": 999.99
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/products")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iPhone 13"))
				.body("description", Matchers.equalTo("Latest model of iPhone"))
				.body("price", Matchers.equalTo(999.99f));
	}

	@Test
	void shouldGetAllProducts() {
		RestAssured
				.given()
				.accept("application/json")
				.when()
				.get("/api/products")
				.then()
				.log().all();
	}
}