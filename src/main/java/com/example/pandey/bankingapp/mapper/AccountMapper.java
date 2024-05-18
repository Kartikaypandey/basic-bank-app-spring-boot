package com.example.pandey.bankingapp.mapper;

import com.example.pandey.bankingapp.dto.AccountDto;
import com.example.pandey.bankingapp.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.id(),
                accountDto.userName(),
                accountDto.balance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto  = new AccountDto(
                account.getId(),
                account.getUserName(),
                account.getBalance()
        );
        return accountDto;
    }
}
