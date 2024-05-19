package com.example.pandey.bankingapp.services.impl;

import com.example.pandey.bankingapp.dto.AccountDto;
import com.example.pandey.bankingapp.dto.TransferDto;
import com.example.pandey.bankingapp.entity.Account;
import com.example.pandey.bankingapp.entity.Transaction;
import com.example.pandey.bankingapp.exception.AccountException;
import com.example.pandey.bankingapp.mapper.AccountMapper;
import com.example.pandey.bankingapp.repository.AccountRepository;
import com.example.pandey.bankingapp.repository.TransactionRepository;
import com.example.pandey.bankingapp.services.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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

        Transaction transaction = getTransaction(id,amount,"CREDIT");
        transactionRepository.save(transaction);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawAmount(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new AccountException("Account does not exist"));

        double newBalance = account.getBalance() - amount;
        if(newBalance < 0){
            throw new AccountException("Insufficient amount");
        }
        account.setBalance(newBalance);

        Account savedAccount = accountRepository.save(account);

        Transaction transaction = getTransaction(id,amount,"DEBIT");
        transactionRepository.save(transaction);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    @Transactional
    public void transferFunds(TransferDto transferDto) {
        Account toAccount = accountRepository
                .findById(transferDto.toAccountId())
                .orElseThrow(()-> new AccountException("Account does not exist"));
        Account fromAccount = accountRepository
                .findById(transferDto.fromAccountId())
                .orElseThrow(()-> new AccountException("Account does not exist"));
        Double deductedAmount = fromAccount.getBalance() - transferDto.amount();

        if(deductedAmount < 0){
            throw new AccountException("Insufficient amount");
        }

        toAccount.setBalance(toAccount.getBalance() + transferDto.amount());
        fromAccount.setBalance(fromAccount.getBalance() - transferDto.amount());
        Transaction transactionOne = getTransaction(transferDto.toAccountId(),transferDto.amount(),"CREDIT");
        Transaction transactionTwo = getTransaction(transferDto.fromAccountId(),transferDto.amount(),"DEBIT");

        transactionRepository.save(transactionOne);
        transactionRepository.save(transactionTwo);
        accountRepository.save(toAccount);
        accountRepository.save(fromAccount);

    }

    public Transaction getTransaction(Long accountId, Double amount , String transactionType){
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setTimeStamp(LocalDateTime.now());
        return transaction;
    }
}
