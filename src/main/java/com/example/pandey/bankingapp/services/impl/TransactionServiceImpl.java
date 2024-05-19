package com.example.pandey.bankingapp.services.impl;

import com.example.pandey.bankingapp.dto.TransactionDto;
import com.example.pandey.bankingapp.entity.Transaction;
import com.example.pandey.bankingapp.mapper.TransactionMapper;
import com.example.pandey.bankingapp.repository.TransactionRepository;
import com.example.pandey.bankingapp.services.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDto> getAllTransactionForGivenId(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountIdOrderByTimeStampDesc(accountId);
        List<TransactionDto> transactionDtoList = transactions
                .stream()
                .map((transaction -> TransactionMapper.mapToTransactionDto(transaction)))
                .collect(Collectors.toList());

        return transactionDtoList;
    }
}
