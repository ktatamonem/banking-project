package com.mk.backend.assignement.banking.request.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateAccountRequestDto {

    @NotNull(message = "CustomerId is not present ")
    private Long customerId;

    @NotNull(message = "Initial Balance is not present")
    private Double initialBalance ;
}
