package com.example.pandey.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
//public class AccountDto {
//    private Long id;
//    private String userName;
//    private Double balance;
//}


public record AccountDto(Long id , String userName , Double balance) {

}
