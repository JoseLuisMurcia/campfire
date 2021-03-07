package es.urjc.etsii.dad.campfire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LandingPostController {

    @GetMapping("make-public-post")
    public String getMakePublicPost() {
        return "html/landing-page/make-public-post.html";
    }

    @PostMapping("publish-post")
    public String postPublishMessage(@RequestParam String message) {
        return "redirect:/home";
    }
}
