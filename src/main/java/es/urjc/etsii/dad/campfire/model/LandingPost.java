package es.urjc.etsii.dad.campfire.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LandingPost {

    @Id
    private long id;
    private String post;
    private boolean isAnonymous;
    @ManyToOne
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
