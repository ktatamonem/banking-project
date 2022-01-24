package com.mk.backend.assignement.banking.service.account;

import com.mk.backend.assignement.banking.entities.account.Account;
import com.mk.backend.assignement.banking.entities.customer.Customer;
import com.mk.backend.assignement.banking.entities.transaction.Transaction;
import com.mk.backend.assignement.banking.exceptions.ObjectNotFoundException;
import com.mk.backend.assignement.banking.repositories.account.AccountRepository;
import com.mk.backend.assignement.banking.service.customer.CustomerService;
import com.mk.backend.assignement.banking.service.transaction.TransactionService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService  ;

    @MockBean
    private AccountRepository accountRepository  ;

    @MockBean
    private TransactionService transactionService  ;

    @MockBean
    private CustomerService customerService ;


    @SneakyThrows
    @BeforeEach
    public void init(){
        Account account = new Account();
        account.setId(1L);
        account.setBalance(100.0);
        account.setAccountNumber("BA123456789");
        doReturn(account).when(accountRepository).findByAccountNumber("BA123456789");
        Customer customer  = new Customer() ;
        customer.setId(1L);
        customer.setUsername("customer_user");
        doReturn(customer).when(customerService).findCustomerById(1L);
        when(accountRepository.save(any(Account.class))).then(returnsFirstArg());
    }

    @Test
    public void test_find_account_by_account_number_success() throws ObjectNotFoundException {
        Account account = accountService.findByAccountNumber("BA123456789");
        Assertions.assertEquals(account.getId() , 1L );
        Assertions.assertEquals(account.getBalance() , 100.0 );

    }

    @Test
    public void test_find_account_by_account_number_failed()  {
        Assertions.assertThrows(ObjectNotFoundException.class,() -> {
            Account account = accountService.findByAccountNumber("BA100");

        });
    }

    @Test
    public void test_create_account() throws ObjectNotFoundException {
        doReturn(new Transaction()).when(transactionService).depositInCash(anyDouble(),any(Account.class));
        Account account =accountService.createAccount(1L,10.0);
        verify(accountRepository,times(1)).save(any(Account.class));
        verify(transactionService,times(1)).depositInCash(eq(10.0),any(Account.class));
        Assertions.assertEquals(account.getCustomer().getUsername() , "customer_user");
    }

    @Test
    public void test_create_account_failed() throws ObjectNotFoundException {
        doThrow(new RuntimeException()).when(transactionService).depositInCash(anyDouble(),any(Account.class));
        Assertions.assertThrows(Exception.class,() -> {
            Account account =accountService.createAccount(1L,10.0);
        });
    }




}
