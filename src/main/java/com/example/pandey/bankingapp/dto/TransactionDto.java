package com.example.pandey.bankingapp.dto;

import java.time.LocalDateTime;

public record TransactionDto(Long id , Long accountId, Double amount,  String transactionType, LocalDateTime timeStamp) { }
