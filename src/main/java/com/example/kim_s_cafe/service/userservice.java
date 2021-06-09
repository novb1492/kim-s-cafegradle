package com.example.kim_s_cafe.service;



import com.example.kim_s_cafe.config.security;
import com.example.kim_s_cafe.model.user.userdao;
import com.example.kim_s_cafe.model.user.userdto;
import com.example.kim_s_cafe.model.user.uservo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class userservice {
    
    private final boolean yes=true;
    private final boolean no=false;

    @Autowired
    private userdao userdao; 
    @Autowired
    private security security;//@Autowired해주고 
    @Autowired
    private utilservice utilservice; 
    

    public boolean checkemail(String email) {

        System.out.println(email+"중복검사");
       uservo vo=userdao.findByEmail(email);
        if(vo==null)///일단 학원가기전까지는 이방법이 제일 편리 한거같다 20200514
        {
             return yes;
        }
     return no;
    }
    public boolean checkRandomNumber(userdto userdto) {
        try {
            String dbnumber=userdao.findByEmailRandnum(userdto.getEmail());
            System.out.println("dbnum"+dbnumber+"rand"+userdto.getRandnum());
            if(dbnumber.equals(userdto.getRandnum())){

                userdao.updateemailcheck("true", userdto.getEmail());
                return yes;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }
    public boolean sendrandomnumber(String email,String randomnumber) {

        try {
            System.out.println("랜덤넘버"+randomnumber);
            userdao.upateEmailConfrimNum(randomnumber, email);
            return yes;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return no;
    }
    public boolean insertmember(userdto userdto) {

        try {    
        BCryptPasswordEncoder encoder=security.encoderpwd();///암호리턴받고
        uservo uservo=userdto.DtoToUservo(userdto);
        String hashpwd=encoder.encode(uservo.getPwd());//자체함수 소환해서 해쉬해주고
        uservo.setPwd(hashpwd);//셋해서
        uservo.setRole("ROLE_USER");
        uservo.setEmailcheck("false");
        uservo.setRandnum(utilservice.GetRandomNum(6));
        userdao.save(uservo);///넣어준다!
        return yes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return no;
    }

    /*public String getemail() {
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails details=(UserDetails)principal;
        System.out.println(details.getUsername()+"조회이메일");
        return details.getUsername();
    }*///공부하기 전에 쓰던방식
    public uservo getinfor(String email) {
        uservo uservo=userdao.findByEmail(email);
        System.out.println(uservo.getName()+"다녀왔니");
        return uservo;
    }
    public boolean checkpwdwithdbpwd(String pwd,String email) 
    {
        BCryptPasswordEncoder bCryptPasswordEncoder=security.encoderpwd();
        String dbpwd=userdao.getpwd(email);
        if(bCryptPasswordEncoder.matches(pwd, dbpwd)){
            return yes;
        }
        return no;
    }
    @Transactional
    public boolean updatepwd(String email,String pwd) {

        try {
            BCryptPasswordEncoder bCryptPasswordEncoder=security.encoderpwd();
            String hashpwd=bCryptPasswordEncoder.encode(pwd);
            uservo uservo=userdao.findByEmail(email);
            uservo.setPwd(hashpwd);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return yes;
    }

}
