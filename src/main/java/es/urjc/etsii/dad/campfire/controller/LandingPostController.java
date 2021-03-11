package es.urjc.etsii.dad.campfire.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.etsii.dad.campfire.service.LandingPostService;

@Controller
public class LandingPostController {

    @Autowired
    private LandingPostService landingPostService;

    @GetMapping("make-public-post")
    public String getMakePublicPost() {
        return "html/landing-page/make-public-post.html";
    }

    @RequestMapping("publish-post")
    public String postPublishMessage(@RequestParam String message, boolean isAnonymous, HttpSession session) {
        landingPostService.storeLandingPost(message, isAnonymous, session.getAttribute("username").toString());
        return "redirect:/home";
    }
}
