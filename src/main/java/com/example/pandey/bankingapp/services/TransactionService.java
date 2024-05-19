package com.example.pandey.bankingapp.services;

import com.example.pandey.bankingapp.dto.TransactionDto;
import com.example.pandey.bankingapp.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TransactionService {

    List<TransactionDto> getAllTransactionForGivenId(Long accountId);
}
