package es.urjc.etsii.dad.campfire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.urjc.etsii.dad.campfire.entity.Chat;
import es.urjc.etsii.dad.campfire.repository.ChatRoomsRepository;

public class ChatRoomsService {
    
    @Autowired
    private ChatRoomsRepository chatRoomsRepository;

    public void save(Chat chat){
        chatRoomsRepository.save(chat);
    }

    public Optional<Chat> findById(long id){
        return chatRoomsRepository.findById(id);
    }

    public List<Chat> findAll(){
        return chatRoomsRepository.findAll();
    }
}
