package com.mk.backend.assignement.banking.controller.transaction;

import com.mk.backend.assignement.banking.request.dto.DepositRequestDto;
import com.mk.backend.assignement.banking.entities.transaction.Transaction;
import com.mk.backend.assignement.banking.navigation.Navigation;
import com.mk.backend.assignement.banking.service.transaction.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Navigation.TRANSACTION_API)
public class TransactionController {

    private final static Logger logger = LogManager.getLogger(TransactionController.class);

    private TransactionService transactionService ;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("deposit")
    public ResponseEntity<Transaction> depositMoney(@Validated @RequestBody DepositRequestDto depositRequestDto){
        logger.info("Starting deposit operation for :{}", depositRequestDto);
        try{
            Transaction transaction = transactionService.depositInCash(depositRequestDto.getAmount() , depositRequestDto.getSourceAccount());
            return ResponseEntity.ok(transaction);
        }catch (Exception ex){
            logger.error("Exception raised while deposit operation for {} ", depositRequestDto , ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null) ;
        }

    }


}
