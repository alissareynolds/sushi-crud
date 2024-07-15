package com.example.sushi_practice_crud.exceptions;

public class SushiNotFoundException extends RuntimeException{
    public SushiNotFoundException(String message) {
        super(message);
    }
}
