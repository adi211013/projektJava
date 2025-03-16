package com.example.finanse;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String message)
    {
        super(message);
    }
}
