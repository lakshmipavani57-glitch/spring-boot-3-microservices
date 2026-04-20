package com.practice.micorservices.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//We can also use RestTemplate or WebClient or RestClient(New as of Spring boot 3 for synchronous comm) to call other microservices
/*
@FeignClient(value = "inventory-service", url = "${inventory.service.url}")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
} */

//Spring OpenFeign is deprecated in Spring Boot 3, so we are using RestClient here for synchronous communication.
//For OpenFeign we define a class whereas with RestClient we define interface, and configure a bean
// (RestClient or RestTemplate(Deprecated) or WebClient(comes from webFlux, so if we want to use this we have to webflux dependencies))
//We are using RestClient here, which provides the implementation of the interface, and we can use that bean to call the methods defined in the interface.

public interface InventoryClient {

    @GetExchange("/api/inventory")
    @Retry(name = "inventory-service") //This name should match instance name we defined in application.yaml
    @CircuitBreaker(name = "inventory-service", fallbackMethod = "fallBackMethod") //This name should match instance name we defined in application.yaml
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallBackMethod(String skuCode, Integer quantity, Throwable throwable) {
        return false; // Return a default value
    }
}
