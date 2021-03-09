package es.urjc.etsii.dad.campfire.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.etsii.dad.campfire.model.User;
import es.urjc.etsii.dad.campfire.repository.UserRepository;

@Controller
public class FriendListController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/friend-list")
    public String getFriendList(Model model, HttpSession session) {
        User user = userRepo.findByUsername(session.getAttribute("username").toString()).get();
        model.addAttribute("friendList", user.getFriends());
        return "friends/friend-list";
    }

    @PostMapping("/send-friend-request")
    public String postFriendRequest(Model model, HttpSession session) {
        User user = userRepo.findByUsername(session.getAttribute("username").toString()).get();
        return "friend-request-sent";
    }
}
