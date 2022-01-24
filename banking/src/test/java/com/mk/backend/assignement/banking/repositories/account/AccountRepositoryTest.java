package com.mk.backend.assignement.banking.repositories.account;

import com.mk.backend.assignement.banking.entities.account.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties ="classpath:application-jpa.properties" )
@Sql({"classpath:sql/init.sql","classpath:sql/account_init.sql"})
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository  accountRepository ;


    @Test
    public void test_find_by_accountnumber(){
        Account account  = accountRepository.findByAccountNumber("BA123456");
        Assertions.assertNotNull(account);
        Assertions.assertEquals(account.getId() , 1L);
        Assertions.assertEquals(account.getBalance() , 100.0);
        Assertions.assertEquals(account.getCustomer().getId() , 4L);

    }

    @Test
    public void test_calculate_sum_balance_by_customerId(){
        Double balance = accountRepository.calculateSumBalanceByCustomerId(4L);
        Assertions.assertNotNull(balance);
        Assertions.assertEquals(balance , 250.0);
    }
}
