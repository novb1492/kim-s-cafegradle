package com.example.kim_s_cafe.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;


import org.springframework.stereotype.Service;

@Service
public class timestampservice {

   private LocalDateTime localDateTime;
   private final boolean before=false;
   private final boolean after=true;
  

    public void setdates(Timestamp timestamp){
        this.localDateTime=timestamp.toLocalDateTime();//20210601이거쓰면 한방에 변환이 된다 완전좋다
    }
    public boolean checktoday() {

        LocalDateTime today = LocalDateTime.now();
        System.out.println(this.localDateTime+"비교할날짜"+today+"오늘날짜");

        if(this.localDateTime.isBefore(today)){///쥑인다...
            return before;
        }
        return after;
    }
     
    
    
}
