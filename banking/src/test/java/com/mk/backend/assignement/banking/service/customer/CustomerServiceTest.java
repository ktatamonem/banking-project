package com.mk.backend.assignement.banking.service.customer;

import com.mk.backend.assignement.banking.entities.customer.Customer;
import com.mk.backend.assignement.banking.exceptions.ObjectNotFoundException;
import com.mk.backend.assignement.banking.repositories.customer.CustomerRepository;
import com.mk.backend.assignement.banking.service.account.AccountService;
import com.mk.backend.assignement.banking.service.transaction.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService ;

    @MockBean
    private CustomerRepository  customerRepository  ;

    @MockBean
    private AccountService accountService ;

    @MockBean
    private TransactionService  transactionService  ;

    @BeforeAll
    public void init(){
        Customer customer  = new Customer()  ;
        customer.setId(1L);
        customer.setUsername("username1");
        doReturn(Optional.of(customer)).when(customerRepository).findById(1L);


    }


    @Test
    public void test_find_customer_by_id_success() throws ObjectNotFoundException {
        Customer customer  = customerService.findCustomerById(1L) ;
        Assertions.assertEquals(customer.getId(), 1L );
        Assertions.assertEquals(customer.getUsername(), "username1");
    }

    @Test
    public void test_find_customer_by_id_failed() {
        Assertions.assertThrows(ObjectNotFoundException.class,() -> {

           customerService.findCustomerById(100L);
        }) ;
    }


}
