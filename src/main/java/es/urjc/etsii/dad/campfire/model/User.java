package es.urjc.etsii.dad.campfire.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long id;
    private String username;
    private String password;

    @ManyToMany
    List<User> friends = new ArrayList<User>();
    @ManyToMany
    List<User> friendRequests = new ArrayList<User>();

    protected User() {
    }

    public User(String username, String password) {
        super();
        this.setUsername(username);
        this.setPassword(password);
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, username='%s', password='%s']", id, username, password);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(List<User> friendRequests) {
        this.friendRequests = friendRequests;
    }

}