package es.urjc.etsii.dad.campfire.model;

//import java.awt.Color;

//import javax.persistence.Entity;

import javax.persistence.Id;
//import javax.persistence.OneToOne;
import javax.persistence.OneToOne;

import org.springframework.web.socket.WebSocketSession;

//@Entity
public class Avatar {
    private int primaryColor;
    private int secondaryColor;
    private int eyesColor;
    private int hatId;
    private int accessoryId;

    private WebSocketSession session; // Lo tiene que tener
    // Primary key
    @Id
    int id;
    @OneToOne
    private User user;

    public Avatar(int id, int pColor, int sColor, int eColor, int hatId, int acessoryId) {
        this.id = id;
        this.primaryColor = pColor;
        this.secondaryColor = sColor;
        this.eyesColor = eColor;
        this.hatId = hatId;
        this.accessoryId = acessoryId;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public int[] getAttributes()
    {
        int[] _attributes = {primaryColor, secondaryColor, eyesColor, hatId, accessoryId};
        return _attributes;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public int getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public int getEyesColor() {
        return eyesColor;
    }

    public void setEyesColor(int eyesColor) {
        this.eyesColor = eyesColor;
    }

    public int getHatId() {
        return hatId;
    }

    public void setHatId(int hatId) {
        this.hatId = hatId;
    }

    public int getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(int accessoryId) {
        this.accessoryId = accessoryId;
    }

   //public User getUser() {
   //    return user;
   //}

   //public void setUser(User user) {
   //    this.user = user;
   //}
}