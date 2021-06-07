package com.example.kim_s_cafe.config.auth;

import java.util.Collection;
import java.util.Map;

import com.example.kim_s_cafe.model.user.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class principaldetail implements UserDetails,OAuth2User{

    private uservo uservo;
   private Map<String,Object>attributes;

    public principaldetail(uservo uservo)
    {
        this.uservo=uservo;
    }
    //oauth로그인
    public principaldetail(uservo uservo,Map<String,Object>attributes){
        this.uservo=uservo;
        this.attributes=attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return uservo.getPwd();
    }

    @Override
    public String getUsername() {
        return uservo.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        
        return attributes;
    }

    @Override
    public String getName() {
    
        return (String)attributes.get("name");
    }


    
    
}
