package com.kunal.exam_portal.service;

import java.util.Set;

import com.kunal.exam_portal.entity.User;
import com.kunal.exam_portal.entity.UserRole;

public interface UserService {
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(String username) throws Exception;
    public void deleteUser(String username) throws Exception;
}
