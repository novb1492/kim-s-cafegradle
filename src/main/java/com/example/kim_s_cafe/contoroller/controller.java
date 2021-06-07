package com.example.kim_s_cafe.contoroller;




import java.util.List;

import com.example.kim_s_cafe.config.auth.principaldetail;
import com.example.kim_s_cafe.model.board.boardvo;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import com.example.kim_s_cafe.model.user.uservo;
import com.example.kim_s_cafe.service.boardservice;
import com.example.kim_s_cafe.service.commentservice;
import com.example.kim_s_cafe.service.contentservice;
import com.example.kim_s_cafe.service.historyservice;
import com.example.kim_s_cafe.service.reservationservice;
import com.example.kim_s_cafe.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class controller {

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
    private historyservice historyservice;
 

    @PostMapping("/auth/joinprocess")
    public String name(uservo uservo) {
        boolean yorn=userservice.insertmember(uservo);
        if(yorn)
        {
            return "loginpage";
        }
        else{
            return "joinpage";
        }
    }
    @GetMapping("/auth/loginpage")
    public String loginpage() {
        return "loginpage";
    }
    @GetMapping("/auth/joinpage")
    public String joinpage() {

        return "joinpage";
        
    }
    @GetMapping("/auth/index")
    public String index() {
        return "index";
        
    }
    @GetMapping("mypage")
    public String mypage(Model model,@AuthenticationPrincipal principaldetail principaldetail) {
    
        String email=principaldetail.getUservo().getEmail();
        model.addAttribute("uservo", userservice.getinfor(email));
        return "mypage";
    }
    @GetMapping("updatepwdpage")
    public String updatepwdpage() {
    
        return "updatepwdpage";
    }
    @GetMapping("/auth/reservationpage")
    public String reservationpage() {
        return "reservationpage";
    }
    @GetMapping("showreservationcepage")
    public String showreservationcepage(Model model,@RequestParam(value="page", defaultValue = "1") int currentpage,@AuthenticationPrincipal principaldetail principaldetail) {
        reservationservice.check24();
        String email=principaldetail.getUservo().getEmail();
        int totalpages=historyservice.counthistorybyemail(email);
        List<reservationvo>array=reservationservice.findreservation(email);
        model.addAttribute("checkdate",reservationservice.confirmdate(array));
        model.addAttribute("currentpage", currentpage);
        model.addAttribute("totalpages", totalpages);
        model.addAttribute("harray", historyservice.gethistory(email,currentpage,totalpages));
        model.addAttribute("array",array);
        model.addAttribute("arraysize", array.size());
        return "showreservationcepage";
    }
    @PostMapping("reservationupdatepage")
    public String reservationupdatepage(reservationvo reservationvo,Model model) {
        model.addAttribute("reservationvo",reservationvo);
        return "reservationupdatepage";
    }
    @GetMapping("/auth/boardlist")
    public String boardlist(Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {  
       model.addAttribute("search",false);
       model.addAttribute("array", boardservice.getboards(currentpage));
       model.addAttribute("totalpage", boardservice.getboards(currentpage).getTotalPages());
       model.addAttribute("currentpage", currentpage);
        return "boardlist";
        
    }
    @GetMapping("/writearticle")
    public String  writepage() {
        
        return "writearticle";
    }
    @GetMapping("/auth/search")
    public String search(@RequestParam("title")String title,Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {
        System.out.println("검색한 키워드 "+title);
        int totalpages=boardservice.getsearchboardscount(title);
        System.out.println("검색한 키워드 총페이지 "+totalpages);
        model.addAttribute("title", title);
        model.addAttribute("search", true);
        model.addAttribute("currentpage", currentpage);
        model.addAttribute("totalpage", totalpages);
        model.addAttribute("array", boardservice.getsearchboards(currentpage, title,totalpages));
        
        return "boardlist"; 
    }
    @GetMapping("/auth/content")
    public String content(@RequestParam("bid")int bid,Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {
        
        System.out.println("들어온 글번호"+bid);
        
        try {
            int totalpages=commentservice.totalcommentcount(bid);
            model.addAttribute("currentpage", currentpage);
            model.addAttribute("totalpage", totalpages);
            model.addAttribute("boardvo", contentservice.getcontent(bid));
            model.addAttribute("array", commentservice.commentpagin(bid, currentpage, totalpages));
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "content";
    }
    @GetMapping("updatecontent")
    public String updatecontent(@RequestParam("bid")int bid,Model model) {
        
        boardvo vo=contentservice.getcontentnohit(bid);
        if(vo!=null){
            model.addAttribute("boardvo", vo);
            return "updatecontent";
        }
         return "updatecontent";  
    }
    @PostMapping("deletecomment")
    public String deletecomment(@RequestParam("bid")int bid,@RequestParam("cid")int cid,Model model,@RequestParam(value="page", defaultValue = "1") int currentpage ) {

        System.out.println("삭제하는 댓글의 게시글번호"+bid+"삭제하는 댓글번호"+cid);
        commentservice.deletecommentbycid(cid);
        int totalpages=commentservice.totalcommentcount(bid);
        model.addAttribute("boardvo", contentservice.getcontent(bid));
        model.addAttribute("bid", bid);
        model.addAttribute("array", commentservice.commentpagin(bid, currentpage, totalpages));
        model.addAttribute("totalpage", totalpages);
        model.addAttribute("currentpage", currentpage);
        return "content";
    }
    

}
