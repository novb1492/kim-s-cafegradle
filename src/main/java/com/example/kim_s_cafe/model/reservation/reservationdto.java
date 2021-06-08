package com.example.kim_s_cafe.model.reservation;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class reservationdto {
    

    private String seat;
    

    private int rid;                                  

  
    private String rname;

  
    private String remail;

    private int requesthour;
    
    private Timestamp reservationdatetime;
}
