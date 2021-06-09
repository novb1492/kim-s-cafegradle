package com.example.kim_s_cafe.contoroller;






import java.util.List;

import javax.validation.Valid;

import com.example.kim_s_cafe.email.EmailUtilImpl;
import com.example.kim_s_cafe.model.board.boarddto;
import com.example.kim_s_cafe.model.comment.commentvo;
import com.example.kim_s_cafe.model.reservation.reservationdto;
import com.example.kim_s_cafe.model.user.userdto;
import com.example.kim_s_cafe.service.commentservice;
import com.example.kim_s_cafe.service.contentservice;
import com.example.kim_s_cafe.service.reservationservice;
import com.example.kim_s_cafe.service.userservice;
import com.example.kim_s_cafe.service.utilservice;
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
    private contentservice contentservice;
    @Autowired
    private commentservice commentservice;
    @Autowired
    private EmailUtilImpl emailUtilImpl;
    @Autowired
    private utilservice utilservice;
    
    @PostMapping("/auth/comfirm")
    public boolean checkemail(@RequestParam("email")String email) {
    
        return userservice.confrimEmail(email);
    }
    @PostMapping("/auth/joinprocess")
    public boolean name(@Valid userdto userdto) {
        System.out.println("회원가입시도"+userdto.getEmail());
       return userservice.insertUser(userdto);
    }
    @PostMapping("updatepwdpageprocess")
    public String updatepwdpageprocess(@RequestParam("email")String email,@RequestParam("pwd")String pwd,@RequestParam("npwd")String npwd,@RequestParam("npwd2")String npwd2) {
       
        if(npwd.equals(npwd2)){
            if(userservice.checkPwdWithDbpwd(pwd, email)){

                if(userservice.updatePwd(email, npwd2)){
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
        return reservationservice.reservationconfirm(seat);
    }
    @PostMapping("reservationprocess")
    public boolean reservationprocess(reservationdto reservationdto,@RequestParam(value = "requesthour[]")List<Integer> requesthour) { ///checkbox로 받을때 value = "파라미터이름[]" 과 List로만 해야한다 20210526
            
            reservationservice.log(reservationdto,requesthour);        
        return reservationservice.insertreservation(reservationdto,requesthour);
    }
    @PostMapping("reservationcancleprocess")
    public boolean reservationcancleprocess(@RequestParam("rid")int rid) {
        System.out.println("예약취소rid= "+rid);
        return reservationservice.deletereservation(rid);
    }
    /*@PostMapping("reservationupdateprocess")
    public boolean reservationupdateprocess(reservationvo reservationvo,@RequestParam(value = "requesthour[]")List<Integer> requesthour) {
        
        System.out.println("변경시도");
        reservationservice.log(reservationvo, requesthour);
        return reservationservice.reservationupdate(reservationvo);
    }*/
    @PostMapping("writearticleprocess")
    public boolean writearticleprocess(@Valid boarddto boarddto) {

      return contentservice.insertArticle(boarddto);
    }
    @PostMapping("updatecontentprocess")
    public boolean updatecontentprocess(@Valid boarddto boarddto) {
        
        return contentservice.updatecontent(boarddto);
        
    }
    @PostMapping("insertcomment")
    public boolean insertcomment(commentvo commentvo) {
        System.out.println("댓글을 시도하는 이메일 "+commentvo.getEmail());
      
        return commentservice.insertComment(commentvo);
    }
    @PostMapping("updatecomment")
    public boolean updatecomment(commentvo commentvo) {
        System.out.println("댓글수정 이메일 "+commentvo.getEmail());
        return commentservice.updateComment(commentvo);
    }
    @PostMapping("deletecomment")
    public String deletecomment(@RequestParam("cid")int cid) {

        System.out.println("삭제하는 댓글번호"+cid);
        commentservice.deletecommentbycid(cid);
  
        return "content";
    }
    @PostMapping("deletearticle")
    public boolean deletearticle(@RequestParam("bid")int bid) {
        boolean yorn=contentservice.deleteArticle(bid);
        boolean yorn2=commentservice.deletecommentbybid(bid);
        if(yorn&&yorn2){
            return true;
        }
        return false;
        
    }
    @PostMapping("/auth/email")
    public boolean email(@RequestParam("email")String email) {
        System.out.println("email전송"+email);
        String randnum=utilservice.GetRandomNum(6);
        userservice.sendEmailRandnum(email,randnum);
        return emailUtilImpl.sendEmail(email, "안녕하세요 kim's cafe입니다", "인증번호는"+randnum+"입니다");
    }
    @PostMapping("/auth/emailpro")
    public boolean emailpro(@RequestParam("email")String email,@RequestParam("randnum")String randnum) {
        System.out.println("인증처리중"+email+randnum);
        return  userservice.checkRandomNumber(email,randnum);
    }
    @PostMapping("/auth/temppwd")
    public boolean temppwd(@RequestParam("email")String email) {
        System.out.println("비밀번호 변경시도 이메일 "+email);
        String temppwd=utilservice.GetRandomNum(8);
        emailUtilImpl.sendEmail(email,"안녕하세요 kim's cafe입니다" ,"임시비밀번호 입니다 : "+temppwd);
        return userservice.updatePwd(email, temppwd);
    }
  
    
}
