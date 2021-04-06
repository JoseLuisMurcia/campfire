package es.urjc.etsii.dad.campfire.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.etsii.dad.campfire.entity.User;
import es.urjc.etsii.dad.campfire.repository.UserRepository;
import es.urjc.etsii.dad.campfire.service.FriendListService;
import es.urjc.etsii.dad.campfire.service.FriendRequestResponse;

@Controller
public class FriendListController {

    @Autowired
    FriendListService friendListService;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/friend-list")
    public String getFriendList(Model model, HttpSession session) {
        List<User> friendList = friendListService.getFriendList(session.getAttribute("username").toString());
        List<User> friendRequests = friendListService.getFriendRequests(session.getAttribute("username").toString());
        model.addAttribute("friendList", friendList);
        model.addAttribute("friendRequests", friendRequests);
        return "friends/friend-list";
    }

    @PostMapping("/send-friend-request")
    public String postFriendRequest(Model model, HttpSession session, String friendRequest) {
        FriendRequestResponse status = friendListService.sendFriendRequest(session.getAttribute("username").toString(),
                friendRequest);

        switch (status) {
        case SENT:
            model.addAttribute("status", "Friend request sent to " + friendRequest);
            break;
        case USER_NOT_FOUND:
            model.addAttribute("status", "That username does not exist.");
            break;
        case USER_ALREADY_FRIEND:
            model.addAttribute("status", "That user is already your friend!");
            break;
        case SELF_REQUEST:
            model.addAttribute("status", "You can't send yourself a friend request... :(");
            break;
        case PENDING_REQUEST:
            model.addAttribute("status", "You have already sent a request to this user.");
            break;
        }

        return "friends/friend-request-sent";
    }

    @PostMapping("/resolve-friend-request")
    public String postResolvedFriendRequest(HttpSession session, String friendRequest, String resolve) {
        if (resolve.equals("decline")) {
            friendListService.declineFriendRequest(friendRequest, session.getAttribute("username").toString());
        } else {
            friendListService.acceptFriendRequest(friendRequest, session.getAttribute("username").toString());
        }
        return "redirect:/friend-list";
    }
}
