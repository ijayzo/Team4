package com.example.demo.Exceptions;

public class PackageDoesNotExist extends RuntimeException{
    public PackageDoesNotExist(){
        super("Package Does Not Exist");
    }
}
