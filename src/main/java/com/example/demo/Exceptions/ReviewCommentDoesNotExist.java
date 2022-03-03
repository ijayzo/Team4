package com.example.demo.Exceptions;

public class ReviewCommentDoesNotExist extends RuntimeException {
    public ReviewCommentDoesNotExist (){
        super("Review Comments Does Not Exist");
    }
}
