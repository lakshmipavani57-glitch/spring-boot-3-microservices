package com.practice.micorservices.stubs;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryStubs {

    public static WireMockServer getMockServer() {
        return new WireMockServer();
    }

    public static void stubInventoryCall(String skuCode, int quantity) {
        getMockServer().stubFor(
                get(urlPathEqualTo("/api/inventory"))
                        .withQueryParam("skuCode", equalTo(skuCode))
                        .withQueryParam("quantity", equalTo(String.valueOf(quantity)))
                        .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")
                        .withStatus(200))
        );
    }
}
