package es.urjc.etsii.dad.campfire.model;
import java.awt.Color;

public class Avatar
{
    public Color primaryColor;
    public Color secondaryColor;
    public Color eyesColor;
    public int hatId;
    public int accessoryId;

    public Avatar(Color pColor, Color sColor, Color eColor, int hatId, int acessoryId)
    {
        this.primaryColor = pColor;
        this.secondaryColor = sColor;
        this.eyesColor = eColor;
        this.hatId = hatId;
        this.accessoryId = acessoryId;
    }
}