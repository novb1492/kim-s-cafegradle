package com.example.kim_s_cafe.model.user;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface userdao extends JpaRepository<uservo,Integer> {///얘는 인터페이스로
    
    @Query(value="select pwd from users where email=?1",nativeQuery = true)
    String getpwd(String pwd);

    public uservo findByEmail(String username);

    @Modifying 
    @Transactional
    @Query(value = "update users u set u.emailconfirmnumber=?1 where u.email=?2",nativeQuery = true)
    public void upateEmailConfrimNum(String randomnumber,String email);

    @Query(value = "select emailconfirmnumber from users where email=?1",nativeQuery = true)
    public int findemailconfirmnumberbyemail(String email); 

    @Modifying 
    @Transactional
    @Query(value = "update users u set u.emailcheck=?1 where u.email=?2",nativeQuery = true)
    public void updateemailcheck(String yes,String email);
}
