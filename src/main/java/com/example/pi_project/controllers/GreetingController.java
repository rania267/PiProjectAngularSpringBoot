package com.example.pi_project.controllers;

import com.example.pi_project.entities.Greeting;
import com.example.pi_project.entities.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greet(HelloMessage message) throws InterruptedException {
        Thread.sleep(2000);
        return new Greeting("Hello, " +
                HtmlUtils.htmlEscape(message.getName()));
}
    @MessageMapping("/hellos")
    @SendTo("/topic/greetingss")
    public Greeting greets(HelloMessage message) throws InterruptedException {
        Thread.sleep(2000);
        return new Greeting("what do you do, " +
                HtmlUtils.htmlEscape(message.getSmth()));
    }
}
