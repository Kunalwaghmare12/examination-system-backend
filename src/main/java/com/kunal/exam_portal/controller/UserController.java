package com.kunal.exam_portal.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunal.exam_portal.entity.Role;
import com.kunal.exam_portal.entity.User;
import com.kunal.exam_portal.entity.UserRole;
import com.kunal.exam_portal.helper.UserFoundException;
import com.kunal.exam_portal.helper.UserNotFoundException;
import com.kunal.exam_portal.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    //creating user

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception{
        Set<UserRole> roles=new HashSet<>();
        Role role =new Role();
        role.setRoleName("NORMAL");
        UserRole userRole =new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return new ResponseEntity<>(userService.createUser(user, roles),HttpStatus.CREATED);
    }

    // getting user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) throws Exception{
        return new ResponseEntity<>(userService.getUser(username),HttpStatus.OK);
    }

    //deleting user

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) throws Exception{
        userService.deleteUser(username);
        return new ResponseEntity<>("user deleted sucessfully",HttpStatus.OK);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserNotFoundException ex){
        String errorMsg= ex.getMessage();
        return new ResponseEntity<>(errorMsg,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        String errorMsg= ex.getMessage();
        return new ResponseEntity<>(errorMsg,HttpStatus.FOUND);
    }

}
