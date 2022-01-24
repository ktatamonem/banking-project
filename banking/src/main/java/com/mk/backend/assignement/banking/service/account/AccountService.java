package com.mk.backend.assignement.banking.service.account;

import com.mk.backend.assignement.banking.entities.account.Account;
import com.mk.backend.assignement.banking.exceptions.ObjectNotFoundException;

public interface AccountService {

    Account findByAccountNumber(String accountNumber) throws ObjectNotFoundException;

    Account createAccount(Long customerId , Double initialBalance) throws ObjectNotFoundException;

    Account saveAccount(Account account);

    Double retrieveAccountsBalanceByCustomerId(Long customerId);

}
