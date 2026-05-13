package com.e_commerce.shop_now.ExceptionHandler;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message){
        super(message);
    }

    
}
