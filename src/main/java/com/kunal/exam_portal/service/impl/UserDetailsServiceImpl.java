package com.kunal.exam_portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kunal.exam_portal.entity.User;
import com.kunal.exam_portal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user !=null){
            UserDetails userDetails=org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build();
            return userDetails;
        }
        throw new UsernameNotFoundException(" User not found with name : "+username);
    }

}
