package com.example.kim_s_cafe.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class utilservice {
    
    public int getrandom() {   
        return 0;
    }
    public int gethour() {
        Calendar cal = Calendar.getInstance();
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour+"현재시간");
        return hour;  
    }
    public Timestamp RequestHourToTimestamp(int requesthour) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String today = sdf.format(date);
        String reservationdatetime=today+" "+requesthour+":0:0";

        return Timestamp.valueOf(reservationdatetime);
    }
}
