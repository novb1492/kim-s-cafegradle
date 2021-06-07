package com.example.kim_s_cafe.config.auth;

import com.example.kim_s_cafe.model.user.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class principalservice implements UserDetailsService{

    @Autowired
    private userdao userdao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       try {
        uservo uservo=userdao.findByEmail(username);
        return new principaldetail(uservo);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }
    
}
