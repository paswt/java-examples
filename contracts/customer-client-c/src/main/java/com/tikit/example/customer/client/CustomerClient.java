package com.tikit.example.customer.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RequiredArgsConstructor
public class CustomerClient {

    private final RestTemplate restTemplate;

    public Collection<Customer> getAllCustomers() {
        ParameterizedTypeReference<Collection<Customer>> ptr = new ParameterizedTypeReference<Collection<Customer>>() {

        };
        ResponseEntity<Collection<Customer>> responseEntity = restTemplate.exchange("http://localhost:8081/customers", HttpMethod.GET, null, ptr);

        return responseEntity.getBody();
    }

}
