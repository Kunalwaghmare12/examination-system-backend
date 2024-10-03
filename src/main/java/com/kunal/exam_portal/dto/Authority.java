package com.kunal.exam_portal.dto;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    private String authority;


    @Override
    public String getAuthority() {
        return this.authority;
    }

}
