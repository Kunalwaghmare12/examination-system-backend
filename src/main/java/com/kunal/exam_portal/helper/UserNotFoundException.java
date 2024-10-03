package com.kunal.exam_portal.helper;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("User with this username is already there in database  || try creating another user");
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}
