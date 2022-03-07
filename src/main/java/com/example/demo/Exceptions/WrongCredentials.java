package com.example.demo.Exceptions;

public class WrongCredentials extends RuntimeException{
    public WrongCredentials(){
        super("Wrong Username or Password");
    }
}
