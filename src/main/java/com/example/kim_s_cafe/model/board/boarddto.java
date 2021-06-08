package com.example.kim_s_cafe.model.board;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class boarddto {
    

    private int bid;

    @Email
    private String email;
    
    @Size(min = 1, max = 1000, message = "1000글자 사이에서 입력해주세요")
    private String content;

    @Size(min = 1, max = 30, message = "30글자 사이에서 입력해주세요")
    private String title;

    private int hit;

    private Timestamp created;

    public boardvo dtotovo() {

        boardvo boardvo=new boardvo();
        boardvo.setContent(this.content);
        boardvo.setEmail(this.email);
        boardvo.setTitle(this.title);
        return boardvo;
    }
}
