package com.example.pandey.bankingapp.services.impl;

import com.example.pandey.bankingapp.dto.AccountDto;
import com.example.pandey.bankingapp.entity.Account;
import com.example.pandey.bankingapp.exception.AccountException;
import com.example.pandey.bankingapp.mapper.AccountMapper;
import com.example.pandey.bankingapp.repository.AccountRepository;
import com.example.pandey.bankingapp.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public AccountDto getSingleAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new AccountException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new AccountException("Account does not exist"));
        accountRepository.deleteById(id);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long id, double amount) {

        Account account =  accountRepository.findById(id).orElseThrow(()-> new AccountException("Account does not exist"));
        double newAmount = account.getBalance() + amount;
        account.setBalance(newAmount);

        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawAmount(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new AccountException("Account does not exist"));

        double newBalance = account.getBalance() - amount;
        if(newBalance<0){
            throw new AccountException("Insufficient amount");
        }
        account.setBalance(newBalance);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }
}
