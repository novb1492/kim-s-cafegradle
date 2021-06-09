package com.example.kim_s_cafe.service;

import java.util.ArrayList;
import java.util.List;

import com.example.kim_s_cafe.model.history.historydao;
import com.example.kim_s_cafe.model.history.historyvo;
import com.example.kim_s_cafe.model.reservation.reservationvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class historyservice {

    private final int pagesize=3;

    @Autowired
    private historydao historydao;
    
    public historyvo inserthistory(reservationvo reservationvo) {
        
        historyvo historyvo=new historyvo();
        historyvo.setRid(reservationvo.getRid());
        historyvo.setRequesthour(reservationvo.getRequesthour());
        historyvo.setReservationdate(reservationvo.getReservationdatetime());
        historyvo.setCreated(reservationvo.getCreated());
        historyvo.setRemail(reservationvo.getRemail());
        historyvo.setRname(reservationvo.getRname());
        historyvo.setSeat(reservationvo.getSeat());
        return historyvo;
    }
    
    @Transactional
    public void updatehistory(reservationvo reservationvo)
    {
        try {
    
            historydao.updatebyrid(reservationvo.getRid(),reservationvo.getRequesthour(),reservationvo.getSeat(),reservationvo.getReservationdatetime(),reservationvo.getCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void inserthistory(historyvo historyvo) {
        try {
            historydao.save(historyvo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        
    }
    public void deletehistory(int rid) {

        try {
            historydao.deletebyrid(rid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public int counthistorybyemail(String email) {

        int count=historydao.countByRemail(email);
        int totalpages=count/pagesize;
        if(count%pagesize>0){
            totalpages++;
        }
        return totalpages;
    }
    public List<historyvo> getHistory(String email,int currentpage,int totalpages) {
        List<historyvo>array=new ArrayList<>();
        try {
            if(totalpages>1){
                int fisrt=(currentpage-1)*pagesize+1;
                int end=fisrt+pagesize-1;
                array=historydao.gethistorybyemail(email,fisrt-1,end-fisrt+1);
            }else{
                array=historydao.gethistorybyemail2(email);
            }
          
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
