package com.mk.backend.assignement.banking.service.account.impl;

import com.mk.backend.assignement.banking.entities.account.Account;
import com.mk.backend.assignement.banking.entities.customer.Customer;
import com.mk.backend.assignement.banking.exceptions.ObjectNotFoundException;
import com.mk.backend.assignement.banking.repositories.account.AccountRepository;
import com.mk.backend.assignement.banking.service.account.AccountService;

import com.mk.backend.assignement.banking.service.customer.CustomerService;
import com.mk.backend.assignement.banking.service.transaction.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Service responsible of the accounts treatment
 */
@Service
public class AccountServiceImpl  implements AccountService {

    private final static Logger logger = LogManager.getLogger(AccountService.class);



    private AccountRepository accountRepository;

    private CustomerService customerService;

    private TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerService customerService , TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.customerService =customerService ;
        this.transactionService = transactionService ;
    }

    /**
     * retrieve account data by accountNumber
     * @param accountNumber
     * @return Account
     * @throws ObjectNotFoundException
     */
    @Override
    public Account findByAccountNumber(String accountNumber) throws ObjectNotFoundException {
        Account account  = accountRepository.findByAccountNumber(accountNumber);
        if(account == null){
            throw new ObjectNotFoundException("Account not found with accountNumber :"+accountNumber);
        }else {
            return account;
        }

    }

    /**
     * Create account and save the initial transaction
     * @param customerId
     * @param initialBalance
     * @return
     * @throws ObjectNotFoundException
     */
    @Override
    public Account createAccount(Long customerId, Double initialBalance) throws ObjectNotFoundException {
        Customer customer = customerService.findCustomerById(customerId);
        Account account  = new Account();
        account.setCustomer(customer);
        account.setCreationDate(new Date());
        account.setAccountNumber(generateAccountNumber(customer));
        account.setBalance(0.0);
        account = accountRepository.save(account);
        if(initialBalance > 0.0){
            try{
                transactionService.depositInCash(initialBalance,account);
            }catch(Exception ex){
                logger.error("Exception while deposit to the account : {} , amount :{} ",account,initialBalance,ex);
                throw ex ;
            }
        }

        return account;

    }

    /**
     *
     * @param account
     * @return
     */
    @Override
    public Account saveAccount(Account account) {
       return this.accountRepository.save(account);
    }

    /**
     * calculate the sum of balance of all the accounts related to the customerId
     * @param customerId
     * @return
     */
    @Override
    public Double retrieveAccountsBalanceByCustomerId(Long customerId) {
        if(customerId !=  null){
            return this.accountRepository.calculateSumBalanceByCustomerId(customerId);
        }
        return 0.0;
    }

    /**
     * generate account number which contains 'BA' as prefix then a random number between [100000,1000000]
     * then the timestamp of the current time and finally the customer id
     * @param customer
     * @return
     */
    private String generateAccountNumber(Customer customer){
        int minimum = 100000;
        int maximum = 1000000 - minimum;
        int randomNumber = (int)((Math.random()*maximum) + minimum);
        return "BA"+randomNumber+ Timestamp.valueOf(LocalDateTime.now()).getTime() + customer.getId() ;

    }
}
