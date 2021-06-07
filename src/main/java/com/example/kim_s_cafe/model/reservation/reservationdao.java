package com.example.kim_s_cafe.model.reservation;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface reservationdao extends JpaRepository<reservationvo,Integer> {
    
    //@Query(value = "select requesthour from reservation where seat=?1",nativeQuery = true)
    List<reservationvo>findBySeat(String seat);//이렇게써도된다

    @Query(value = "select * from reservation where remail=?1 order by rid desc",nativeQuery = true)
    List<reservationvo>findbyemail(String remail);



}
