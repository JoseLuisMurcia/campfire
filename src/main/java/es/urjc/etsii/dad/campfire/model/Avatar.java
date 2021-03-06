package es.urjc.etsii.dad.campfire.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//import java.awt.Color;

//import javax.persistence.Entity;

import javax.persistence.Id;
//import javax.persistence.OneToOne;
import javax.persistence.OneToOne;

@Entity
public class Avatar {
    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private int primaryColor;
    private int secondaryColor;
    private int eyesColor;
    private int hatId;
    private int accessoryId;

    @OneToOne
    private User user;

    public Avatar(){}
    public Avatar(User user, int pColor, int sColor, int eColor, int hatId, int acessoryId) 
    {
        this.user = user;
        this.primaryColor = pColor;
        this.secondaryColor = sColor;
        this.eyesColor = eColor;
        this.hatId = hatId;
        this.accessoryId = acessoryId;
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

   public User getUser() {
       return user;
   }

   public void setUser(User user) {
       this.user = user;
   }
}