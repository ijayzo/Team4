package com.example.demo.Exceptions;

public class UserAlreadyExist extends RuntimeException{

    public UserAlreadyExist(){
        super("User Already Exist, Please Change UserName");
    }
}
