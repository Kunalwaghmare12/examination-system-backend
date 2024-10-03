package com.kunal.exam_portal.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kunal.exam_portal.configuration.JwtUtils;
import com.kunal.exam_portal.dto.JwtRequest;
import com.kunal.exam_portal.service.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;


    // generate-token of user using username and password
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
            
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }

        // authenticate
        UserDetails userdetails = userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtils.generateToken(userdetails);
        return ResponseEntity.ok(token);
    }

    private void authenticate(String username, String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED" + e.getMessage());
        }catch(BadCredentialsException e){
            throw new Exception("Invalid Credentials"+e.getMessage());
        }

    }

    // this Api will return the details of user who is loging
    @GetMapping("/current-user")
    public ResponseEntity<UserDetails> getUser(Principal principal){
        return new ResponseEntity<>((UserDetails)userDetailsServiceImpl.loadUserByUsername(principal.getName()),HttpStatus.OK);

    }
}
