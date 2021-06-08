package com.example.kim_s_cafe.service;

import java.util.ArrayList;
import java.util.List;

import com.example.kim_s_cafe.model.board.boarddao;
import com.example.kim_s_cafe.model.board.boarddto;
import com.example.kim_s_cafe.model.board.boardvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class boardservice {

    private final boolean yes=true;
    private final boolean no=false;
    private final int pagesize=3;
    
    @Autowired
    private boarddao boarddao;

    
    public boolean deletearticle(int bid) {
        try {
            boarddao.deleteById(bid);
            return yes;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return no;
    }
    public boolean insertarticle(boarddto boarddto) {
        try {
            boardvo boardvo=new boardvo(boarddto);
            boarddao.save(boardvo);
            return yes;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return no;
        
    }
    public Page<boardvo> getboards(int currentpage) {

        try {
            return boarddao.findAll(PageRequest.of(currentpage-1, pagesize,Sort.by(Sort.Direction.DESC,"bid")));
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    public int getsearchboardscount(String title) {
        int totalpages=0;
        try {
            int count=boarddao.findallcountbytitle(title);
            totalpages=count/pagesize;
            if(count%pagesize>0){
                totalpages++;
            }
            return totalpages;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return 0;
    }
    public List<boardvo> getsearchboards(int currentpage,String title,int totalpages) {

        List<boardvo>array=new ArrayList<>();
        int fisrt=0,end=0;
        try {
            if(totalpages>1){
                fisrt=(currentpage-1)*pagesize+1;
                end=fisrt+pagesize-1; 
                array=boarddao.findsearch(title,fisrt-1,end-fisrt+1);
            }else{
                array=boarddao.findsearch2(title);
            }
            System.out.println("검색완료 첫번째 게시글번호"+array.get(0).getBid());
        } catch (Exception e) {
           e.printStackTrace();
        }
        return array;
    }
}
