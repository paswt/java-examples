package com.tikit.example.service.customer.controller;


import com.tikit.example.service.customer.entity.Customer;
import com.tikit.example.service.customer.repository.CustomerRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CustomerCtrlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRep customerRep;

    @Test
    public void shouldReturnAllCustomers() throws Exception {

        when(this.customerRep.findAll()).thenReturn(Arrays.asList(new Customer(1L, "Bob"), new Customer(2L, "Lol")));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].name").value("Bob"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
