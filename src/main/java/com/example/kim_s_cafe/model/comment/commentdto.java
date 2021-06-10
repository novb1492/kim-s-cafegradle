package com.example.kim_s_cafe.model.comment;

import java.sql.Timestamp;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class commentdto {
    
    @NotNull
    @NotEmpty
    private int cid;

    @NotNull
    @NotEmpty
    private int bid;

    private String email;

    @NotNull(message = "댓글창이 빈칸입니다")
    @NotEmpty(message = "댓글창이 빈칸입니다")
    private String comment;

    private Timestamp created;
}
