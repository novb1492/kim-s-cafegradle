package com.example.kim_s_cafe.model.reservation;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class reservationdto {
    
    @NotBlank
    private String seat;
    
    private int rid;    

    @NotBlank
    private String rname;

    @Email
    private String remail;
    
    @NotNull
    private int requesthour;
    
    private Timestamp reservationdatetime;
}
