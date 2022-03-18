package com.example.testokenit.exception_handling;

public class NoSuchProductsOnDataException extends RuntimeException{
    public NoSuchProductsOnDataException(String message) {
        super(message);
    }
}
