package com.example.kim_s_cafe.model.user;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class userdto {
    
 
    private int id;

    private String email;

    private String pwd;

    private String name;

    private Timestamp created;

    private String role;

    private String provider;
    
    private String providerid;

    private String emailcheck;
    
    private int emailconfirmnumber;
}
