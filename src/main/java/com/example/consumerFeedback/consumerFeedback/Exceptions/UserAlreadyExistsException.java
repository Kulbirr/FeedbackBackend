package com.example.consumerFeedback.consumerFeedback.Exceptions;

import java.util.concurrent.ExecutionException;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
