package com.mk.backend.assignement.banking.request.dto;

import com.mk.backend.assignement.banking.entities.account.Account;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class DepositRequestDto {

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private Account sourceAccount;
}
