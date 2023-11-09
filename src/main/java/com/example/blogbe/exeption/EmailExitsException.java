package com.example.blogbe.exeption;

public class EmailExitsException extends Exception{
    public EmailExitsException(String message) {
        super(message);
    }
}
