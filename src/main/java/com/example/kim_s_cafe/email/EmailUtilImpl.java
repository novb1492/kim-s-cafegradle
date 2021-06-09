package com.example.kim_s_cafe.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailUtilImpl  {

    @Autowired
    private JavaMailSender sender;//자체적으로 제공해주는 기능

    public boolean sendEmail(String toAddress, String subject, String body) {
       System.out.println(toAddress+"보낼주소");
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
          helper.setTo(toAddress);///보낼주소
          helper.setSubject(subject);///제목
          helper.setText(body);//내용
        } catch (MessagingException e) {
          e.printStackTrace();
          return false;
        }
        sender.send(message);
        return true;
      }
        
       
}
