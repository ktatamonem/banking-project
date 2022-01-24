package com.mk.backend.assignement.banking.service.customer;

import com.mk.backend.assignement.banking.entities.customer.Customer;
import com.mk.backend.assignement.banking.exceptions.ObjectNotFoundException;
import com.mk.backend.assignement.banking.response.dto.CustomerDataResponse;

public interface CustomerService {

    Customer findCustomerById(Long id) throws ObjectNotFoundException;

    CustomerDataResponse retrieveCustomerDateByCustomerId(Long id) throws ObjectNotFoundException;
}
