package com.example.kim_s_cafe.config.oauth;

import com.example.kim_s_cafe.config.auth.principaldetail;
import com.example.kim_s_cafe.config.oauth.provider.facebookuserinfor;
import com.example.kim_s_cafe.config.oauth.provider.googleuserinfor;
import com.example.kim_s_cafe.config.oauth.provider.oauth2userinfor;
import com.example.kim_s_cafe.model.user.userdao;
import com.example.kim_s_cafe.model.user.uservo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class principaloauth2userservice extends DefaultOAuth2UserService {
    
    @Autowired
    private userdao userdao;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        System.out.println("userrequest"+userRequest.getClientRegistration());
        System.out.println("accesstoken"+userRequest.getAccessToken());

        OAuth2User oAuth2User=super.loadUser(userRequest);

        oauth2userinfor oauth2userinfor=null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글로그인요청");
            oauth2userinfor=new googleuserinfor(oAuth2User.getAttributes());

        }else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            System.out.println("페이스북로그인요청");
            oauth2userinfor=new facebookuserinfor(oAuth2User.getAttributes());

        }else{
            System.out.println("우리는 구글 페이스북만 지원합니다");
        }

        //String provider=oauth2userinfor.getProvider();//얘가구글이다/페이스북이다
        String email=oauth2userinfor.getEmail();
        String pwd="1111";
        String role="ROLE_USER";
        
       uservo uservo=userdao.findByEmail(email);
      
        if(uservo==null){
            uservo=new uservo();
            uservo.setEmail(email);
            uservo.setName(oauth2userinfor.getName());
            uservo.setProvider(oauth2userinfor.getProvider());
            uservo.setProviderid(oauth2userinfor.getProviderid());
            uservo.setPwd(pwd);
            uservo.setRole(role);
            uservo.setEmailcheck("true");
            uservo.setRandnum("yes");
            userdao.save(uservo);
        }   
       //회원가입 강제진행후 얘가 지정한 로그인성공 페이지로 보내준다
       return new principaldetail(uservo, oAuth2User.getAttributes());///principal이니 가능하다 이걸하면 authen거기로
    }
}
