package com.example.testokenit.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PricesGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PriceIncorrectDate> handlerException(NoSuchProductsOnDataException exception) {
        PriceIncorrectDate data = new PriceIncorrectDate();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PriceIncorrectDate> handlerException(Exception exception) {
        PriceIncorrectDate data = new PriceIncorrectDate();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
