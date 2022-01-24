package com.mk.backend.assignement.banking.response.dto;

import com.mk.backend.assignement.banking.entities.account.Account;
import com.mk.backend.assignement.banking.entities.transaction.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDataResponse {

    private String firstName  ;

    private String lastName ;

    private Double balance ;

    private List<Account> accounts ;

    private List<Transaction> transactions ;

}
