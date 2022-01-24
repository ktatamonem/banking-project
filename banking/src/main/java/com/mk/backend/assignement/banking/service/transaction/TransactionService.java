package com.mk.backend.assignement.banking.service.transaction;


import com.mk.backend.assignement.banking.entities.account.Account;
import com.mk.backend.assignement.banking.entities.transaction.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction depositInCash(Double amount , Account destinationAccount) ;

    Transaction withdrawalInCash(Double amount , Account sourceAccount) ;

    Transaction transferMoney(Account sourceAccount  , Account destinationAccount  , Double amount);

    List<Transaction> retrieveTransactionListByCustomerId(Long customerId);

}
