package com.example.pandey.bankingapp.services;

import com.example.pandey.bankingapp.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    List<AccountDto> getAllAccounts();
    AccountDto getSingleAccount(Long id);
    AccountDto deleteAccount(Long id);

    AccountDto depositAmount(Long id , double amount);
    AccountDto withdrawAmount(Long id , double amount);


}
