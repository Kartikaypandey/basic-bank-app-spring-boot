package com.example.pandey.bankingapp.mapper;

import com.example.pandey.bankingapp.dto.TransactionDto;
import com.example.pandey.bankingapp.entity.Transaction;

public class TransactionMapper {

    public static Transaction mapToTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDto.transactionType());
        transaction.setTimeStamp(transactionDto.timeStamp());
        transaction.setAmount(transactionDto.amount());
        transaction.setAccountId(transactionDto.accountId());
        return transaction;
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto(transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTimeStamp()
        );

        return transactionDto;
    }


}
