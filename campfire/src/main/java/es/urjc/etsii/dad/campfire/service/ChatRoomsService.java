package es.urjc.etsii.dad.campfire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.urjc.etsii.dad.campfire.entity.Chat;
import es.urjc.etsii.dad.campfire.repository.ChatRoomsRepository;

@Service
public class ChatRoomsService {
    
    @Autowired
    private ChatRoomsRepository chatRoomsRepository;

    @CacheEvict(value = "chats",allEntries = true)
    public void save(Chat chat){
        chatRoomsRepository.save(chat);
    }

    public Optional<Chat> findById(long id){
        return chatRoomsRepository.findById(id);
    }

    @Cacheable("chats")
    public List<Chat> findAll(){
        System.out.println("kachau");
        return chatRoomsRepository.findAll();
    }
}
