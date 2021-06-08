package com.example.kim_s_cafe.model.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class boarddto {
    

    private int bid;

    private String email;

    private String content;

    private String title;

    private int hit;

    private Timestamp created;
}
