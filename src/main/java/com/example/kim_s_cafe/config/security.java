package com.example.kim_s_cafe.config;






import com.example.kim_s_cafe.config.oauth.principaloauth2userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

//구글로그인이 완료된 후처리를 해줘야함
            //1.코드받기(인증)2.엑세스토큰받기(권한생성)
            //3.사용자 프로필정보 가져오기
            //4.그정보를토대로 회원가입 혹은 정보가 좀 모자라면 ex주소라던가
            //모자라면 추가적인 회원가입을 시켜야함
            //구글로그인이 완료되면 (엑세스토큰+사용자프로필정보) 다받음
        


@Configuration//빈등록: 스프링 컨테이너에서 객체에서 관리
@EnableWebSecurity/////필터를 추가해준다
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소 접근을 미리체크 한다  이3개는 셋트임 20210520
public class security extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private principaloauth2userservice principaloauth2userservice;


    @Bean
    public BCryptPasswordEncoder encoderpwd() {

        return new BCryptPasswordEncoder();
    }
    
    @Bean
    SimpleUrlAuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/auth/index");
    }
   

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
         .csrf().disable()///ajax사용하기 위해 토큰은 나중에
         .authorizeRequests()////요청이발생
         .antMatchers("/","/auth/**","/oauth2/")////이 링크들은
         .permitAll()///허용한다
         .anyRequest()///그외 다른 요청운
         .authenticated()//인증이있어야한다(로그인)
        .and()
            .formLogin()////로그인 발생시
            .loginPage("/auth/loginpage")///로그인페이지 지정
            .loginProcessingUrl("/auth/loginprocess")//여기로된링크를 가로채서    protected void configure(AuthenticationManagerBuilder auth) 에서 검사한다
            .defaultSuccessUrl("/auth/index")//성공시 여기로보낸다
        .and()
            .oauth2Login()
            .loginPage("/auth/loginpage")
            .userInfoEndpoint()
            .userService(principaloauth2userservice);
            

         
 
           
        
            
    }
}
