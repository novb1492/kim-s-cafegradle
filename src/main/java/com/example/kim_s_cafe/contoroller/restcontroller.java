package com.example.kim_s_cafe.contoroller;






import java.util.List;

import javax.validation.Valid;

import com.example.kim_s_cafe.email.EmailUtilImpl;
import com.example.kim_s_cafe.model.board.boarddto;
import com.example.kim_s_cafe.model.board.boardvo;
import com.example.kim_s_cafe.model.comment.commentvo;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import com.example.kim_s_cafe.model.user.userdto;
import com.example.kim_s_cafe.service.boardservice;
import com.example.kim_s_cafe.service.commentservice;
import com.example.kim_s_cafe.service.contentservice;
import com.example.kim_s_cafe.service.reservationservice;
import com.example.kim_s_cafe.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restcontroller {


    @Autowired
    private userservice userservice;
    @Autowired
    private reservationservice reservationservice;
    @Autowired
    private boardservice boardservice;
    @Autowired
    private contentservice contentservice;
    @Autowired
    private commentservice commentservice;
    @Autowired
    private EmailUtilImpl emailUtilImpl;
    
    @PostMapping("/auth/comfirm")
    public boolean checkemail(@RequestParam("email")String email) {
    
        return userservice.checkemail(email);
    }
    @PostMapping("updatepwdpageprocess")
    public String updatepwdpageprocess(@RequestParam("email")String email,@RequestParam("pwd")String pwd,@RequestParam("npwd")String npwd,@RequestParam("npwd2")String npwd2) {
       
        if(npwd.equals(npwd2)){
            if(userservice.checkpwdwithdbpwd(pwd, email)){

                if(userservice.updatepwd(email, npwd2)){
                    return "yes";
                }
                else{
                    return "error";
                }
            }
            else{
                return "wrongpwd";
            }
        }
        else{
            return "npwd!=npwd2";
        }
    }
    @PostMapping("reservationconfrim")
    public List<Integer> reservationconfirm(@RequestParam("seat")String seat) {
        reservationservice.check24();
        return reservationservice.reservationconfirm(seat);
    }
    @PostMapping("reservationprocess")
    public boolean reservationprocess(reservationvo reservationvo,@RequestParam(value = "requesthour[]")List<Integer> requesthour) { ///checkbox로 받을때 value = "파라미터이름[]" 과 List로만 해야한다 20210526
            
            reservationservice.log(reservationvo,requesthour);        
        return reservationservice.insertreservation(reservationvo,requesthour);
    }
    @PostMapping("reservationcancleprocess")
    public boolean reservationcancleprocess(@RequestParam("rid")int rid) {
        System.out.println("예약취소rid= "+rid);
        return reservationservice.deletereservation(rid);
    }
    @PostMapping("reservationupdateprocess")
    public boolean reservationupdateprocess(reservationvo reservationvo,@RequestParam(value = "requesthour[]")List<Integer> requesthour) {
        
        System.out.println("변경시도");
        reservationservice.log(reservationvo, requesthour);
        return reservationservice.reservationupdate(reservationvo);
    }
    @PostMapping("writearticleprocess")
    public boolean writearticleprocess(@Valid boarddto boarddto) {

      return boardservice.insertarticle(boarddto.dtotovo());
    }
    @PostMapping("updatecontentprocess")
    public boolean updatecontentprocess(boardvo vo,@RequestParam("bid")int bid) {
        
        return contentservice.updatecontent(bid, vo);
        
    }
    @PostMapping("insertcomment")
    public boolean insertcomment(commentvo commentvo) {
        System.out.println("댓글을 시도하는 이메일 "+commentvo.getEmail());
      
        return commentservice.insertcomment(commentvo);
    }
    @PostMapping("deletearticle")
    public boolean deletearticle(@RequestParam("bid")int bid) {
        boolean yorn=boardservice.deletearticle(bid);
        boolean yorn2=commentservice.deletecommentbybid(bid);
        if(yorn&&yorn2){
            return true;
        }
        return false;
        
    }
    @PostMapping("email")
    public String email(@RequestParam("email")String email) {
        System.out.println("email전송"+email);
        int randomnumber=8795;
        userservice.sendrandomnumber(email,randomnumber);
        emailUtilImpl.sendEmail("novb1492@naver.com", "스프링을 이용한 메일 전송", "인증번호는"+randomnumber+"입니다");
        return "email";
    }
    @PostMapping("emailpro")
    public String emailpro(@RequestParam("email")String email,@RequestParam("randomnumber")int randomnumber) {
        System.out.println("인증처리중"+email+randomnumber);
        userservice.checkrandomnumber(email, randomnumber);
        return "mypage";
    }
    @PostMapping("/auth/joinprocess")
    public boolean name(@Valid userdto userdto) {
        System.out.println("회원가입시도"+userdto.getEmail());
       return userservice.insertmember(userdto);
    }
  
    
}
