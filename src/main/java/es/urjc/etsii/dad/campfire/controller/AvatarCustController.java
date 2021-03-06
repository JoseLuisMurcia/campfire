package es.urjc.etsii.dad.campfire.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class AvatarCustController
{


    @GetMapping("/customization")
    public String GoToAvatarWindow(Model _model)
    {
        return "avatar-customization";
    }
}