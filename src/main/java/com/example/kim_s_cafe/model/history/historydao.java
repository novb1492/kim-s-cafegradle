package com.example.kim_s_cafe.model.history;


import org.springframework.data.jpa.repository.Modifying;

import java.sql.Timestamp;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface historydao extends JpaRepository<historyvo,Integer> {
    
    @Query(value = "select *from history where remail=?1 order by hid desc limit ?2,?3",nativeQuery = true)
    List<historyvo>getHistoryByEmailNative(String email,int first,int end);

    //@Query(value = "select count(*) from history where remail=?1",nativeQuery = true)
    int countByRemail(String email); 

    @Query(value = "select *from history where remail=?1 order by hid desc",nativeQuery = true)
    List<historyvo>getHistoryByEmail2Native(String email);

    @Modifying 
    @Transactional
    @Query(value = "delete from history h where h.rid=?1",nativeQuery = true)
    void deleteByRidNative(int rid);

    @Modifying 
    @Transactional
    @Query(value = "update history h set h.requesthour=?2,h.seat=?3,h.reservationdate=?4,h.created=?5 where h.rid=?1",nativeQuery = true)///20210603jpa update쿼리문쓰는법!
    void updateByRidNative(int rid,int requesthour,String seat,Timestamp reservationdate,Timestamp created);
}
