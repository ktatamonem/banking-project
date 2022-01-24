package com.mk.backend.assignement.banking.service.transaction.impl;

import com.mk.backend.assignement.banking.entities.account.Account;
import com.mk.backend.assignement.banking.entities.transaction.Transaction;
import com.mk.backend.assignement.banking.repositories.transaction.TransactionRepository;
import com.mk.backend.assignement.banking.service.account.AccountService;
import com.mk.backend.assignement.banking.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * A service responsible of all the transactions
 * @author m.ktata
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository  ;

    @Autowired
    @Lazy
    private AccountService accountService  ;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * deposit money in cash into bank account
     * @param amount
     * @param destinationAccount
     * @return Transaction
     */
    @Override
    public Transaction depositInCash(Double amount, Account destinationAccount) {
        Transaction  transaction  = new Transaction() ;
        transaction.setDestinationAccount(destinationAccount);
        transaction.setAmount(amount);
        transaction.setCreationDate(new Date());
        transaction.setInCash(Boolean.TRUE);
        transaction =transactionRepository.save(transaction);
        destinationAccount.setBalance(destinationAccount.getBalance() +amount);
        accountService.saveAccount(destinationAccount);
        return transaction;
    }

    /**
     * withdrawal amount of money from account in cash
     * @param amount
     * @param sourceAccount
     * @return Transaction
     */
    @Override
    public Transaction withdrawalInCash(Double amount, Account sourceAccount) {
       Transaction transaction = new Transaction();
       transaction.setSourceAccount(sourceAccount);
       transaction.setAmount(amount);
       transaction.setInCash(Boolean.TRUE);
       transaction.setCreationDate(new Date());
       transaction =transactionRepository.save(transaction);
       sourceAccount.setBalance(sourceAccount.getBalance() - amount);
       accountService.saveAccount(sourceAccount);
       return transaction ;
    }

    /**
     * transfer money between two accounts
     * @param sourceAccount
     * @param destinationAccount
     * @param amount
     * @return
     */
    @Override
    public Transaction transferMoney(Account sourceAccount, Account destinationAccount, Double amount) {
       Transaction transaction= new Transaction()  ;
       transaction.setSourceAccount(sourceAccount);
       transaction.setDestinationAccount(destinationAccount);
       transaction.setAmount(amount);
       transaction.setCreationDate(new Date());
       transaction.setInCash(Boolean.FALSE);
       transaction =transactionRepository.save(transaction)  ;
       sourceAccount.setBalance(sourceAccount.getBalance() - amount);
       destinationAccount.setBalance(destinationAccount.getBalance() + amount);
       accountService.saveAccount(sourceAccount);
       accountService.saveAccount(destinationAccount);
       return transaction;
    }

    /**
     * retrieve list of transactions which is the customer is a part of it (destination or source)
     * @param customerId
     * @return
     */
    @Override
    public List<Transaction> retrieveTransactionListByCustomerId(Long customerId) {
        return transactionRepository.retrieveTransactionsByCustomerId(customerId);
    }
}
