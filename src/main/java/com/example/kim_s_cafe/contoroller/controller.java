package com.example.kim_s_cafe.contoroller;




import java.util.List;


import com.example.kim_s_cafe.config.auth.principaldetail;
import com.example.kim_s_cafe.model.board.boardvo;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import com.example.kim_s_cafe.service.boardservice;
import com.example.kim_s_cafe.service.commentservice;
import com.example.kim_s_cafe.service.contentservice;
import com.example.kim_s_cafe.service.historyservice;
import com.example.kim_s_cafe.service.reservationservice;
import com.example.kim_s_cafe.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 
    @GetMapping("/auth/findpwdpage")
    public String findpwdpage() {
        return "findpwdpage";
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
        System.out.println("이름 "+principaldetail.getUservo().getName());///uservo를 그대로 사용가능 20210608
        model.addAttribute("uservo", userservice.getInfor(email));
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
    public String showReservationcePage(Model model,@RequestParam(value="page", defaultValue = "1") int currentpage,@AuthenticationPrincipal principaldetail principaldetail) {
        String email=principaldetail.getUservo().getEmail();
        int totalpages=historyservice.counthistorybyemail(email);
        List<reservationvo>array=reservationservice.findReservation(email);
        model.addAttribute("currentpage", currentpage);
        model.addAttribute("totalpages", totalpages);
        model.addAttribute("harray", historyservice.getHistory(email,currentpage,totalpages));
        model.addAttribute("array",reservationservice.confirmDate(array));
        return "showreservationcepage";
    }
    @PostMapping("reservationupdatepage")
    public String reservationUpdatePage(reservationvo reservationvo,Model model) {
        model.addAttribute("reservationvo",reservationvo);
        return "reservationupdatepage";
    }
    @GetMapping("/auth/boardlist")
    public String boardList(Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {  
       Page<boardvo>array=boardservice.getBoard(currentpage);
       model.addAttribute("search",false);
       model.addAttribute("array", array);
       model.addAttribute("totalpage", array.getTotalPages());
       model.addAttribute("currentpage", currentpage);
        return "boardlist";
        
    }
    @GetMapping("/auth/search")
    public String search(@RequestParam("title")String title,Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {
        System.out.println("검색한 키워드 "+title);
        int totalpages=boardservice.getSearchAtBoardCount(title);
        System.out.println("검색한 키워드 총페이지 "+totalpages);
        model.addAttribute("title", title);
        model.addAttribute("search", true);
        model.addAttribute("currentpage", currentpage);
        model.addAttribute("totalpage", totalpages);
        model.addAttribute("array", boardservice.getSearchAtBoard(currentpage, title,totalpages));
        
        return "boardlist"; 
    }
    @GetMapping("/writearticlepage")
    public String writePage() {
  
        return "writearticlepage";
    }
    @GetMapping("/auth/content")
    public String content(@RequestParam("bid")int bid,Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {
        
        System.out.println("들어온 글번호"+bid);
        
        try {
            int totalpages=commentservice.totalCommentCount(bid);
            model.addAttribute("currentpage", currentpage);
            model.addAttribute("totalpage", totalpages);
            model.addAttribute("boardvo", contentservice.getcontent(bid));
            model.addAttribute("array", commentservice.commentPage(bid, currentpage, totalpages));
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "content";
    }
    @GetMapping("updatecontent")
    public String updateContent(@RequestParam("bid")int bid,Model model) {
        
        boardvo vo=contentservice.getcontentnohit(bid);
        if(vo!=null){
            model.addAttribute("boardvo", vo);
            return "updatecontent";
        }
         return "updatecontent";  
    }
 
    

}
