package com.practice.micorservices;

import org.springframework.boot.SpringApplication;

public class TestOrderServiceNewApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderServiceNewApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
