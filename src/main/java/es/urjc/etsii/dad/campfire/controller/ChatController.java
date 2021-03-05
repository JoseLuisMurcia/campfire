package es.urjc.etsii.dad.campfire.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.urjc.etsii.dad.campfire.model.Chat;
import es.urjc.etsii.dad.campfire.model.ChatMessage;
import es.urjc.etsii.dad.campfire.repository.ChatMessageRepository;
import es.urjc.etsii.dad.campfire.repository.ChatRoomsRepository;

@Controller
public class ChatController {
    
    @Autowired
    private ChatRoomsRepository chatRepository;

    @GetMapping("/chat_lobby")
    public String getChat(Model model, HttpSession session){
        List<Chat> chats = chatRepository.findAll();
        model.addAttribute("chats", chats);
        String name = (String)session.getAttribute("username");
        model.addAttribute("username", "name");
        return "chat/chat_lobby";
    }

    @GetMapping("/chat_room/{id}")
    public String getChatRoom(Model model, @PathVariable long id, HttpSession session){
        Optional<Chat> optionalChat = chatRepository.findById(id);
        if(optionalChat.isPresent()){
            model.addAttribute("chat", optionalChat.get());
        }
        String name = (String)session.getAttribute("username");
        model.addAttribute("username", "name");
        return "chat/chat_room";
    }


    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("/mychats")
    public String getMyChats(Model model){
        List<ChatMessage> chatmsgs = chatMessageRepository.findAll();
        List<Chat> chats = chatRepository.findAll();
        model.addAttribute("chatmsg", chatmsgs);
        model.addAttribute("chats", chats);
        return "chat/test";
    }


}
