package com.tikit.example.customer.client;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@SpringBootTest(classes = {CustomerClient.class, RestTemplate.class})
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.tikit.example:customer-service:+:stubs:8081")
class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void clientShouldReturnAllCustomers(){
        Collection<Customer> customers = customerClient.getAllCustomers();

        BDDAssertions.then(customers).size().isEqualTo(2);
        BDDAssertions.then(customers.iterator().next().getId()).isEqualTo(1L);
        BDDAssertions.then(customers.iterator().next().getName()).isEqualTo("Bob");
    }

}