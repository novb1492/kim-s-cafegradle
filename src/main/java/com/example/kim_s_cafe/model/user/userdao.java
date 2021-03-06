package com.example.kim_s_cafe.model.user;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface userdao extends JpaRepository<uservo,Integer> {///얘는 인터페이스로
    
    @Query(value="select pwd from users where email=?1",nativeQuery = true)
    String getPwdNative(String pwd);

    public uservo findByEmail(String username);

    @Modifying 
    @Transactional
    @Query(value = "update users u set u.randnum=?1 where u.email=?2",nativeQuery = true)
    public void upateEmailConfrimNumNative(String randomnumber,String email);

    @Query(value = "select randnum from users where email=?1",nativeQuery = true)
    public String findByEmailRandnumNative(String email); 

    @Modifying 
    @Transactional
    @Query(value = "update users u set u.emailcheck=?1 where u.email=?2",nativeQuery = true)
    public void updateEmailCheckNative(String yes,String email);
}
