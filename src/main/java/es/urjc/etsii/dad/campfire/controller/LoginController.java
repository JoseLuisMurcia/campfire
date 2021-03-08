package es.urjc.etsii.dad.campfire.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.etsii.dad.campfire.model.User;
import es.urjc.etsii.dad.campfire.service.LoginResponse;
import es.urjc.etsii.dad.campfire.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/register")
    public String getRegister() {
        return "html/landing-page/register.html";
    }

    @PostMapping("register-done")
    public String postRegister(@RequestParam String username, @RequestParam String password) {
        LoginResponse registerResponse = loginService.registerUser(new User(username, password));

        if (registerResponse == LoginResponse.SUCCESS) {
            return "redirect:/";
        } else {
            return "redirect:/register";
        }
    }

    @PostMapping("login-done")
    public String postLogin(Model model, String username, String password, HttpSession session) {
        LoginResponse loginResponse = loginService.loginUser(new User(username, password));
        session.setAttribute("username", username);
        if (loginResponse == LoginResponse.SUCCESS) {
            model.addAttribute("username", username);
            return "redirect:/home";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("home")
    public String getHome(Model model, HttpSession session) {
        model.addAttribute("username", session.getAttribute("username"));
        return "home/home";
    }

    @GetMapping("log-out")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
