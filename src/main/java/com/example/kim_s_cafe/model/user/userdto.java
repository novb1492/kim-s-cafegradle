package com.example.kim_s_cafe.model.user;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class userdto {
    
    
    private int id;

    @NotEmpty
    @Email(message = "이메일형식으로 써주세요")
    private String email;
    
    @NotEmpty
    @Size(min = 4,message = "비밀번호가 짧습니다")
    private String pwd;

    @NotEmpty
    private String name;

    private Timestamp created;

    private String role;

    private String provider;
    
    private String providerid;

    private String emailcheck;
    
    private int emailconfirmnumber;

    public uservo DtoToUservo(userdto userdto) {
        uservo uservo=new uservo();
        uservo.setEmail(userdto.getEmail());
        uservo.setName(userdto.getName());
        uservo.setProvider(userdto.getProvider());
        uservo.setProviderid(userdto.getProviderid());
        uservo.setEmailcheck(userdto.getEmailcheck());
        uservo.setEmailconfirmnumber(userdto.getEmailconfirmnumber());
        uservo.setPwd(userdto.getPwd());
        uservo.setRole(userdto.getRole());
        return uservo;
    }
}
