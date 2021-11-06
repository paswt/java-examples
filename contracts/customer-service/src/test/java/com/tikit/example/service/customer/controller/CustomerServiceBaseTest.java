package com.tikit.example.service.customer.controller;


import com.tikit.example.service.customer.entity.Customer;
import com.tikit.example.service.customer.repository.CustomerRep;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerServiceBaseTest {

    @Autowired
    private CustomerCtrl customerCtrl;

    @MockBean
    private CustomerRep customerRep;

    @BeforeEach
    void shouldReturnAllCustomers() {

        when(customerRep.findAll()).thenReturn(Arrays.asList(new Customer(1L, "Bob"), new Customer(2L, "Lol")));

        RestAssuredMockMvc.standaloneSetup(customerCtrl);

    }
}
