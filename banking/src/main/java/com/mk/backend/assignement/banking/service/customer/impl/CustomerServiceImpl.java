package com.mk.backend.assignement.banking.service.customer.impl;

import com.mk.backend.assignement.banking.entities.customer.Customer;
import com.mk.backend.assignement.banking.entities.transaction.Transaction;
import com.mk.backend.assignement.banking.exceptions.ObjectNotFoundException;
import com.mk.backend.assignement.banking.repositories.customer.CustomerRepository;
import com.mk.backend.assignement.banking.response.dto.CustomerDataResponse;
import com.mk.backend.assignement.banking.service.account.AccountService;
import com.mk.backend.assignement.banking.service.customer.CustomerService;
import com.mk.backend.assignement.banking.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service responsible of all treatments related to Customer
 */
@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository  customerRepository  ;

    @Autowired
    @Lazy
    private AccountService accountService  ;

    @Autowired
    @Lazy
    private TransactionService transactionService  ;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository  =  customerRepository  ;
    }

    /**
     * retrieve customer data by customer id
     * @param id
     * @return Customer
     * @throws ObjectNotFoundException
     */
    @Override
    public Customer findCustomerById(Long id) throws ObjectNotFoundException {
             Optional<Customer> customer = this.customerRepository.findById(id) ;
             if (customer.isPresent()){
                 return customer.get();
             }else {
                 throw new ObjectNotFoundException("Customer not found with id: "+id);
             }

    }

    /**
     * Retrieve Customer data the balance of all the related accounts  and the transactions where the customer is involved
     * @param id
     * @return CustomerDataResponse
     * @throws ObjectNotFoundException
     */
    @Override
    public CustomerDataResponse retrieveCustomerDateByCustomerId(Long id) throws ObjectNotFoundException {
            CustomerDataResponse response  = new CustomerDataResponse() ;
            Customer customer = this.findCustomerById(id);
            Double accountsBalance = accountService.retrieveAccountsBalanceByCustomerId(id) ;
            List<Transaction> transactionList  = transactionService.retrieveTransactionListByCustomerId(id);
            response.setBalance(accountsBalance);
            response.setFirstName(customer.getFirstName());
            response.setLastName(customer.getLastName());
            response.setTransactions(transactionList);
            return response;
    }
}
