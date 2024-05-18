package com.example.pandey.bankingapp.services.impl;

import com.example.pandey.bankingapp.dto.AccountDto;
import com.example.pandey.bankingapp.entity.Account;
import com.example.pandey.bankingapp.mapper.AccountMapper;
import com.example.pandey.bankingapp.repository.AccountRepository;
import com.example.pandey.bankingapp.services.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
}