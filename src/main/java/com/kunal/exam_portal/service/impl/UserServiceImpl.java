package com.kunal.exam_portal.service.impl;


import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kunal.exam_portal.entity.User;
import com.kunal.exam_portal.entity.UserRole;
import com.kunal.exam_portal.helper.UserFoundException;
import com.kunal.exam_portal.helper.UserNotFoundException;
import com.kunal.exam_portal.repository.RoleRepository;
import com.kunal.exam_portal.repository.UserRepository;
import com.kunal.exam_portal.service.UserService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // creating user
    @Transactional
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local=userRepository.findByUsername(user.getUsername());

        if(local !=null){
            log.info("user is present with name : "+local.getUsername());
            throw new UserFoundException();
        }else{
            for(UserRole ur:userRoles){
                this.roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            local = userRepository.save(user);
        }

        return local;
        
    }

    //getting user by username
    @Override
    public User getUser(String username) throws Exception {

        User foundUser=userRepository.findByUsername(username);
        if(foundUser==null){
            throw new UserNotFoundException();
        }
        return foundUser;
    }

    @Override
    public void deleteUser(String username) throws Exception {
        boolean isAccountExist = userRepository.existsByUsername(username);
        if(!isAccountExist){
            throw new UserNotFoundException();
        }
        User userToDelete = userRepository.findByUsername(username);
        userRepository.delete(userToDelete);

    }

}
