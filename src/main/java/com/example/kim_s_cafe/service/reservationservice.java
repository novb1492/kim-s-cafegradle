package com.example.kim_s_cafe.service;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;


import com.example.kim_s_cafe.model.history.historyvo;
import com.example.kim_s_cafe.model.reservation.reservationdao;
import com.example.kim_s_cafe.model.reservation.reservationdto;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class reservationservice {

    private final boolean yes=true;
    private final boolean no=false;
    private final byte opentime=6;
    private final byte endtime=23;

    @Autowired
    private reservationdao reservationdao;
    @Autowired
    private historyservice historyservice;
    @Autowired
    private timestampservice timestampservice;

    
    public List<Boolean> confirmdate(List<reservationvo>array) {

        if(array.size()>0){
            List<Boolean>checkdate=new ArrayList<>();
            for(int i=0;i<array.size();i++){
                timestampservice.setdates(array.get(i).getReservationdatetime());
                boolean yorn=timestampservice.checktoday();
                if(yorn){
                    checkdate.add(true);
                }else{
                    checkdate.add(false);
                }
            }
            return checkdate;
        }
        return null;
    }
    @Transactional
    public boolean reservationupdate(reservationvo reservationvo) {
        try {
            reservationvo.setSeat(reservationvo.getSeat());
            reservationvo.setRequesthour(reservationvo.getRequesthour());
            Timestamp timestamp=gettimestamp(reservationvo.getRequesthour());
            reservationvo.setReservationdatetime(timestamp);
            reservationvo.setCreated(reservationvo.getCreated());
            reservationdao.save(reservationvo);
            historyservice.updatehistory(reservationvo);
            return yes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

    public boolean deletereservation(int rid) {
        try {
                reservationdao.deleteById(rid);
                historyservice.deletehistory(rid);
                return yes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

    public List<reservationvo> findreservation(String email) {
        try {
            List<reservationvo>array=reservationdao.findByRemailOrderByRidDesc(email);
            return array;  
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
    }

    public void log(reservationdto reservationdto,List<Integer> requesthour) {
            for(int i=0;i<requesthour.size();i++){
            System.out.println("예약을 시도하는시간"+requesthour.get(i));  
            }
            System.out.println("예약을 시도 하는 자리"+reservationdto.getSeat());
            System.out.println("예약을 시도하는이메일 "+reservationdto.getRemail());
            System.out.println("예약을 시도하는이름 "+reservationdto.getRname());
    }  
    public List<Integer> reservationconfirm(String seat) {

        List<Integer>array2=new ArrayList<>();
	    int hour = gethour();
        try {

            List<reservationvo>array=reservationdao.findBySeat(seat);
        
            if(!array.isEmpty()){///비어있나 확인해야함
                for(int ii=opentime;ii<=endtime;ii++){
                    for(int i=0;i<array.size();i++){
                            if(ii==array.get(i).getRequesthour()||ii<=hour){
                                System.out.println(ii+"이미 예약"); 
                                break;   
                            }else{  
                                if(i==array.size()-1){
                                    System.out.println(ii+"자바스크립트"); 
                                    array2.add(ii);
                                }
                            }
                    }
                } 
            }
            else{
                for(int i=opentime;i<=endtime;i++){
                    if(i>hour){
                        System.out.println(i+"자바스크립트"); 
                        array2.add(i);  
                    }
                }
            }
            return array2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean insertreservation(reservationdto reservationdto,List<Integer> requesthour) {
        
            try {  
                for(int i=0;i<requesthour.size();i++){
                    reservationdto.setRequesthour(requesthour.get(i));
                    Timestamp timestamp=gettimestamp(requesthour.get(i));
                    reservationdto.setReservationdatetime(timestamp);
                    reservationvo reservationvo=new reservationvo(reservationdto);///20210528그래준영아 객체를 비워줘야지.. 안그러면 update만 되잖아..
                    reservationdao.save(reservationvo);  
                    historyvo historyvo= historyservice.inserthistory(reservationvo);
                    historyservice.inserthistory(historyvo);
                }               
                return yes;
            } catch (Exception e) {
                e.printStackTrace();      
            }
            return no;  
    }
    public void check24() {
        if(gethour()==0){
            reservationdao.deleteAll();
            System.out.println("24시가지나  모든 예약이 삭제됩니다");
        }
    }
    public int gethour() {
        Calendar cal = Calendar.getInstance();
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour+"현재시간");
        return hour;  
    }
    private Timestamp gettimestamp(int requesthour){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String today = sdf.format(date);
        String reservationdatetime=today+" "+requesthour+":0:0";

        return Timestamp.valueOf(reservationdatetime);

    }
 
    
}
