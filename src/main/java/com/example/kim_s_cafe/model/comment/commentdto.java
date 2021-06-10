package com.example.kim_s_cafe.model.comment;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class commentdto {
    
    @NotNull
    private int cid;

    @NotNull
    private int bid;

    @Email
    @NotNull
    private String email;
    
    @NotNull
    private String comment;

    private Timestamp created;
}
