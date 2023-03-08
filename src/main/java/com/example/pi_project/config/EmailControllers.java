package com.example.pi_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailControllers {
    @Autowired
    JavaMailSender mailSender;


    @GetMapping("/test")
    public String send(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("asmazr865@gmail.com");
        message.setTo("naouali.aziza@esprit.tn");
        message.setSubject("Wellness Wings Mailing Bot");
        message.setText("Content of the message");

        mailSender.send(message);

        return "done";
    }
    public String AffectationEvent(String Mail ,String name  )
    {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("asmazr865@gmail.com");
        message.setTo(Mail);
        message.setText("Dear Client :"+name+" welcome in our event we wish that will donate for the poverty!! ");
        message.setSubject("Wellness Wings Mailing Bot");
        mailSender.send(message);

        return "Successfully sent";
    }
    public String ApplicationMail(String Mail , String candid , String Off , String datea)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("WellnessWings@gmail.com");
        message.setTo("naouali.aziza@esprit.tn");
        message.setText("Mr/Mrs : "+candid+" \n "+" Your application for : " + Off + " \n " +"At :" +datea+ " : Has been successful !");
        message.setSubject("Wellness Wings Mailing Bot");
        mailSender.send(message);

        return "Successfully sent";
    }

}
