package com.tikit.example.service.customer.repository;

import com.tikit.example.service.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRep extends JpaRepository<Customer, Long> {
}
