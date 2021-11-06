package com.tikit.example.customer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

@SpringBootTest(classes = {CustomerClient.class, RestTemplate.class})
@AutoConfigureWireMock(port = 8081)
@AutoConfigureJson
class CustomerClientWireTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CustomerClient customerClient;

    @Test
    void clientShouldReturnAllCustomers() throws JsonProcessingException {

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/customers"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(200)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBody(objectMapper.writeValueAsString(
                                        Arrays.asList(
                                                new Customer(1L, "Bob"),
                                                new Customer(2L, "Lol")
                                        )
                                ))
                )
        );

        Collection<Customer> customers = customerClient.getAllCustomers();

        BDDAssertions.then(customers).size().isEqualTo(2);
        BDDAssertions.then(customers.iterator().next().getId()).isEqualTo(1L);
        BDDAssertions.then(customers.iterator().next().getName()).isEqualTo("Bob");
    }

}