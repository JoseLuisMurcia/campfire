package es.urjc.etsii.dad.campfire.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long id;
    private String username;
    private String password;

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

}