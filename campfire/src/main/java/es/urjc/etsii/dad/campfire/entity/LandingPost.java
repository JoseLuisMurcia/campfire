package es.urjc.etsii.dad.campfire.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Landing_Post")
public class LandingPost implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String post;
    private boolean isAnonymous;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User owner;

    protected LandingPost() {
    }

    public LandingPost(String post, boolean isAnonymous, User owner) {
        this.post = post;
        this.isAnonymous = isAnonymous;
        this.owner = owner;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

}
