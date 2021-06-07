package com.example.kim_s_cafe.model.history;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
@Data
@Table(name="history")
@Entity
public class historyvo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hid",nullable = false)
    private int hid;

    @Column(name="seat",nullable = false)
    private String seat;
    

    @Column(name="rid",nullable = false)
    private int rid;                                  

    @Column(name="rname",nullable = false)
    private String rname;

    @Column(name="remail",nullable = false)
    private String remail;

    @Column(name = "created")
    @CreationTimestamp
    private Timestamp created;

    @Column(name="requesthour",nullable = false)
    private int requesthour;

    @Column(name = "reservationdate",nullable = false)
    private Timestamp reservationdate;

}
