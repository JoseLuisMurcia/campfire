package es.urjc.etsii.dad.campfire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.urjc.etsii.dad.campfire.model.LandingPost;
import es.urjc.etsii.dad.campfire.service.LandingPostService;

@Controller
public class LandingPageController {

    @Autowired
    private LandingPostService landingPostService;

    @GetMapping("/")
    public String getLandingPage(Model model) {
        LandingPost publicPost = landingPostService.getRandomLandingPost();
        model.addAttribute("public-post-msg", publicPost.getPost());
        if (publicPost.isAnonymous()) {
            model.addAttribute("public-post-owner", "Anonymous...");
        } else {
            model.addAttribute("public-post-owner", publicPost.getOwner().getUsername());
        }
        return "home/landing-page";
    }

}
