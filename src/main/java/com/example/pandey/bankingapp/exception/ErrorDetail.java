package com.example.pandey.bankingapp.exception;

import java.time.LocalDateTime;

public record ErrorDetail(String errorMessage, String errorDetail , String errorCode , LocalDateTime timestamp) {

}
