package es.urjc.etsii.dad.campfire.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.urjc.etsii.dad.campfire.entity.Chat;
import es.urjc.etsii.dad.campfire.service.ChatRoomsService;

@Controller
public class ChatController {
    
    @Autowired
    private ChatRoomsService chatRoomsService;

    @GetMapping("/chat_lobby")
    public String getChat(Model model, HttpSession session){
        model.addAttribute("chats", chatRoomsService.findAll());
        model.addAttribute("userName", (String)session.getAttribute("username"));
        return "chat/chat_lobby";
    }

    @GetMapping("/chat_room/{id}")
    public String getChatRoom(Model model, @PathVariable long id, HttpSession session){
        Optional<Chat> optionalChat = chatRoomsService.findById(id);
        if(optionalChat.isPresent()){
            model.addAttribute("chat", optionalChat.get());
        }
        model.addAttribute("userName", (String)session.getAttribute("username"));
        return "chat/chat_room";
    }

}
