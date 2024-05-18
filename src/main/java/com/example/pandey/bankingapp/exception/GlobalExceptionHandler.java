package com.example.pandey.bankingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorDetail> handleAccountException(AccountException accountException, WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
                accountException.getMessage(),
                webRequest.getDescription(false),
                "Account does not exist",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleOtherException(Exception exception, WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
                exception.getMessage(),
                webRequest.getDescription(false),
                "Internal Server",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
