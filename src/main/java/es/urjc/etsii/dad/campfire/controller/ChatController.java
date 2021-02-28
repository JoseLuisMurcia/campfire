package es.urjc.etsii.dad.campfire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    
    @GetMapping("/chat_lobby")
    public String getChat(){
        return "html/chat.html";
    }
}
