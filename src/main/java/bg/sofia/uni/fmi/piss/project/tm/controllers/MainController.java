package bg.sofia.uni.fmi.piss.project.tm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    String index() {
        return "index";
    }

    @GetMapping("/registration")
    String registration() {
        return "registration";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    @GetMapping("/users")
    String users() {
        return "users";
    }

    @GetMapping("/myProfile")
    String myProfile() { return "myProfile.html"; }

    @GetMapping("/courses")
    String courses() {
        return "courses";
    }
}
