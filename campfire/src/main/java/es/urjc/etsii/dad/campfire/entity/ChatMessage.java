package es.urjc.etsii.dad.campfire.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ChatMessage implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String text;

    @ManyToOne
    private User user;

    protected ChatMessage(){}

    public ChatMessage(String text, User user){
        super();
        this.text = text;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
