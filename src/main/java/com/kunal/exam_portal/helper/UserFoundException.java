package com.kunal.exam_portal.helper;

public class UserFoundException extends Exception {
    
    public UserFoundException(){
        super("User with username already exist !!");
    }

    public UserFoundException(String msg){
        super(msg);
    }
}
