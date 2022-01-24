package com.mk.backend.assignement.banking.service.transaction;

import com.mk.backend.assignement.banking.entities.account.Account;
import com.mk.backend.assignement.banking.entities.transaction.Transaction;
import com.mk.backend.assignement.banking.repositories.transaction.TransactionRepository;
import com.mk.backend.assignement.banking.service.account.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTest {


    @Autowired
    private TransactionService transactionService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void init(){

        when(accountService.saveAccount(any(Account.class))).then(returnsFirstArg());
        when(transactionRepository.save(any(Transaction.class))).then(returnsFirstArg());
    }

    @Test
    public void test_deposit_in_cash(){
        Account  account = new Account()  ;
        account.setId(1L);
        account.setBalance(100.0);
        Transaction transaction  = transactionService.depositInCash(120.0,account);
        Assertions.assertEquals(transaction.getDestinationAccount().getId(),1L);
        Assertions.assertEquals(transaction.getDestinationAccount().getBalance(),220.0);
        Assertions.assertEquals(transaction.getAmount() ,120.0);
    }


    @Test
    public void test_withdrawal_in_cash(){
        Account  account = new Account()  ;
        account.setId(1L);
        account.setBalance(100.0);
        Transaction transaction  = transactionService.withdrawalInCash(50.0,account);
        Assertions.assertEquals(transaction.getSourceAccount().getId(),1L);
        Assertions.assertEquals(transaction.getSourceAccount().getBalance(),50.0);
        Assertions.assertEquals(transaction.getAmount() ,50.0);
    }


    @Test
    public void test_transfer_money(){
        Account  srcAccount = new Account()  ;
        srcAccount.setId(1L);
        srcAccount.setBalance(100.0);
        Account  destAccount = new Account()  ;
        destAccount.setId(2L);
        destAccount.setBalance(150.0);
        Transaction transaction  = transactionService.transferMoney(srcAccount,destAccount,50.0);
        Assertions.assertEquals(transaction.getSourceAccount().getId(),1L);
        Assertions.assertEquals(transaction.getSourceAccount().getBalance(),50.0);
        Assertions.assertEquals(transaction.getDestinationAccount().getId(),2L);
        Assertions.assertEquals(transaction.getDestinationAccount().getBalance(),200.0);
        Assertions.assertEquals(transaction.getAmount() ,50.0);
    }





}
