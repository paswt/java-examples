package com.tikit.example.service.customer.controller;

import com.tikit.example.service.customer.entity.Customer;
import com.tikit.example.service.customer.repository.CustomerRep;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomerCtrl {

    private final CustomerRep customerRep;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){

        return customerRep.findAll();
    }

}
