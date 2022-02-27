package com.example.demo.Exceptions;

/**
 * throws Exception when user does not exist
 */
public class UserAlreadyExist extends RuntimeException{

    public UserAlreadyExist(){
        super("User Already Exist, Please Change UserName");
    }
}
