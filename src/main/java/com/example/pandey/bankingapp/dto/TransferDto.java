package com.example.pandey.bankingapp.dto;

public record TransferDto(Long toAccountId, Long fromAccountId,Double amount) {

}
