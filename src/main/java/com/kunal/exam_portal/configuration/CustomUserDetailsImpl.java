package com.kunal.exam_portal.configuration;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kunal.exam_portal.dto.Authority;
import com.kunal.exam_portal.entity.User;
import com.kunal.exam_portal.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomUserDetailsImpl implements UserDetails{

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        Set<UserRole> roles = user.getUserRoles();
        roles.forEach(role ->{
            authorities.add(new Authority(role.getRole().getRoleName()));
        });
        return authorities;
        
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
