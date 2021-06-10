package com.example.kim_s_cafe.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import com.example.kim_s_cafe.model.comment.commentdao;
import com.example.kim_s_cafe.model.comment.commentdto;
import com.example.kim_s_cafe.model.comment.commentvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class commentservice {

    private final int pagesize=3;
    private final boolean yes=true;
    private final boolean no=false;

    @Autowired
    private commentdao commentdao;

    public List<commentvo> commentpagin(int bid,int currentpage,int totalpages) {
        
        
       List<commentvo>array=new ArrayList<>();

       try {
            if(totalpages>0){
                int fisrt=(currentpage-1)*pagesize+1;
                int end=fisrt+pagesize-1;
                array=commentdao.findByonebyone(bid,fisrt-1,end-fisrt+1);
                }else{
                    currentpage=0;
                    totalpages=0;
                } 
                return array;
       } catch (Exception e) {
           e.printStackTrace();
       }
     
        return null;
    }
    public int totalcommentcount(int bid) {

        int count=commentdao.countByBid(bid);
        int totalpages=count/pagesize;
        if(count%pagesize>0){
            totalpages++;
        }
        System.out.println(bid+"글의 총 댓글"+count);
        System.out.println("댓글총페이지"+totalpages);
        return totalpages;
    }
    public boolean insertComment(commentdto commentdto) {

        try {
            commentvo commentvo=new commentvo(commentdto);
            commentdao.save(commentvo);
            return yes;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return no;
    }
    @Transactional
    public boolean updateComment(commentdto commentdto) {

        try {
            commentvo commentvo=commentdao.findByCid(commentdto.getCid());
            commentvo.setComment(commentdto.getComment());
            commentdao.save(commentvo);
            return yes;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return no;
    }
    public boolean deleteCommentByCid(int cid) {
        try {
            commentdao.deleteById(cid);
            return yes;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return no;
        
    }
    public boolean deletecommentbybid(int bid) {

        try {
            commentdao.deleteBybidNative(bid);
            return yes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }
    public boolean eqalsEmail(String email,int cid) {
        try {
            if(email.equals(commentdao.findById(cid).get().getEmail()))
            return yes;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return no;
        
    }
}
