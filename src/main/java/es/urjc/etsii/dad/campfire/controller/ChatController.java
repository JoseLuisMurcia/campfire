package es.urjc.etsii.dad.campfire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.urjc.etsii.dad.campfire.model.Chat;
import es.urjc.etsii.dad.campfire.service.ChatService;

@Controller
public class ChatController {
    
    @Autowired
    private ChatService chatService;

    @GetMapping("/chat_lobby")
    public String getChat(Model model){
        model.addAttribute("chats", chatService.findAll());
        model.addAttribute("username", "Federico");
        return "chat/chat_lobby";
    }

    @GetMapping("/chat_room/{id}")
    public String getChatRoom(Model model, @PathVariable long id){
        Chat chat = chatService.findById(id);
        model.addAttribute("chat", chat);
        model.addAttribute("username", "Federico");
        return "chat/chat_room";
    }


}
