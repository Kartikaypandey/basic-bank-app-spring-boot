package com.example.pandey.bankingapp.controller;

import com.example.pandey.bankingapp.dto.AccountDto;
import com.example.pandey.bankingapp.entity.Account;
import com.example.pandey.bankingapp.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getSingleAccount(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getSingleAccount(id),HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id , @RequestBody Map<String,Double> map){
        return new ResponseEntity<>(accountService.depositAmount(id,map.get("amount")),HttpStatus.OK);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id , @RequestBody Map<String, Double> request){
        return new ResponseEntity<>(accountService.withdrawAmount(id,request.get("amount")),HttpStatus.OK);
    }
}
