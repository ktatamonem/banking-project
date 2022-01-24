package com.mk.backend.assignement.banking.repositories.transaction;

import com.mk.backend.assignement.banking.entities.transaction.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties ="classpath:application-jpa.properties" )
@Sql({"classpath:sql/init.sql","classpath:sql/account_init.sql" , "classpath:sql/transaction_init.sql"})
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository  transactionRepository;


    @Test
    public void test_retrieve_transactions_by_customerId(){
        List<Transaction> transactionList  = transactionRepository.retrieveTransactionsByCustomerId(4L);
        Assertions.assertFalse(transactionList.isEmpty());
        Assertions.assertEquals(transactionList.size(),3);
    }
}
