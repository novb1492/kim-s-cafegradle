package com.example.kim_s_cafe.service;

import javax.transaction.Transactional;

import com.example.kim_s_cafe.model.board.boarddao;
import com.example.kim_s_cafe.model.board.boardvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class contentservice {

    private final boolean yes=true;
    private final boolean no=false;

    @Autowired
    private boarddao boarddao;
 

    public boardvo getcontent(int bid) {

        boardvo vo=boarddao.findById(bid).orElseThrow();
        vo.setHit(vo.getHit()+1);
        boarddao.save(vo);///조회수 늘려주는 알고리즘 사라져서 다시 만듬!
        return vo;
    }
    public boardvo getcontentnohit(int bid) {
        try {
            boardvo boardvo= boarddao.findById(bid).orElseThrow(null);
            return boardvo;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
        
    }
    @Transactional
    public boolean updatecontent(int bid, boardvo vo) {

        try {
            boardvo boardvo=boarddao.findById(bid).orElseThrow();
            boardvo.setTitle(vo.getTitle());
            boardvo.setContent(vo.getContent());
            return yes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }
    
    
}
