package es.urjc.etsii.dad.campfire.model;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class Chat {
    
    private Long id;
    private String name;
    private ConcurrentMap<Long,String> messages = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong();

    public Chat(String name){
        this.name = name;
    }
    
    public Long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public Collection<String> findAll(){
        return messages.values();
    }

    public void addMessage(String message){
        long id = nextId.getAndIncrement();
        this.messages.put(id,message);
    }
}
