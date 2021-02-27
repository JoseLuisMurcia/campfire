package es.urjc.etsii.dad.campfire.model;
import java.awt.Color;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Avatar
{
    private Color primaryColor;
    private Color secondaryColor;
    private Color eyesColor;
    private int hatId;
    private int accessoryId;
    //Primary key
    @OneToOne
    private User user;

    public Avatar(Color pColor, Color sColor, Color eColor, int hatId, int acessoryId)
    {
        this.primaryColor = pColor;
        this.secondaryColor = sColor;
        this.eyesColor = eColor;
        this.hatId = hatId;
        this.accessoryId = acessoryId;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Color getEyesColor() {
        return eyesColor;
    }

    public void setEyesColor(Color eyesColor) {
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