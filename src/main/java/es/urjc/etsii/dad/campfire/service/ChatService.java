package es.urjc.etsii.dad.campfire.service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.urjc.etsii.dad.campfire.model.Chat;

@Service
public class ChatService {
    
    private ConcurrentMap<Long,Chat> chats = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong();

    public ChatService()
    {

    }

    public Collection<Chat> findAll(){
        return chats.values();
    }

    public Chat findById(long id){
        return chats.get(id);
    }

    public void addChat(Chat chat){
        long id = nextId.getAndIncrement();
        chat.setId(id);
        this.chats.put(id, chat);
    }

    public void deleteById(long id){
        this.chats.remove(id);
    }
}
