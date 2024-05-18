package com.example.pandey.bankingapp.controller;

import com.example.pandey.bankingapp.dto.AccountDto;
import com.example.pandey.bankingapp.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        AccountDto savedAccountInfo = accountService.createAccount(accountDto);
        return new ResponseEntity<>(savedAccountInfo,HttpStatus.CREATED);
    }
}
