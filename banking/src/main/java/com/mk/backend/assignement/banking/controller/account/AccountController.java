package com.mk.backend.assignement.banking.controller.account;

import com.mk.backend.assignement.banking.request.dto.CreateAccountRequestDto;
import com.mk.backend.assignement.banking.entities.account.Account;
import com.mk.backend.assignement.banking.exceptions.ObjectNotFoundException;
import com.mk.backend.assignement.banking.navigation.Navigation;
import com.mk.backend.assignement.banking.service.account.AccountService;
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
@RequestMapping(Navigation.ACCOUNT_API)
public class AccountController {

    private final static Logger logger = LogManager.getLogger(AccountController.class);

    private AccountService accountService  ;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("create")
    public ResponseEntity<Account> createAccount(@Validated @RequestBody CreateAccountRequestDto createAccountRequestDto){
        logger.info("Starting creating account for createAccountRequest : {}", createAccountRequestDto);
        try {
            Account account = accountService.createAccount(createAccountRequestDto.getCustomerId() , createAccountRequestDto.getInitialBalance());
            return ResponseEntity.ok(account);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity("Customer Not Found",HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            logger.error("Exception raised while creating account for {}", createAccountRequestDto , ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
