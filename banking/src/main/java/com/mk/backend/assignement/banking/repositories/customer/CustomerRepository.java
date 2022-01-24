package com.mk.backend.assignement.banking.repositories.customer;

import com.mk.backend.assignement.banking.entities.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
