package com.example.kim_s_cafe.model.comment;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class commentdto {
    

    private int cid;
    
    private int bid;

    private String email;

    private String comment;

    private Timestamp created;
}
