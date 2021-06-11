package com.example.kim_s_cafe.model.reservation;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class reservationdto {
    
    @NotNull
    @NotEmpty
    private String seat;
    
    private int rid;    

    private String rname;

    @Email
    private String remail;
    
    @NotNull
    @NotEmpty
    private int requesthour;
    
    private Timestamp reservationdatetime;
}
