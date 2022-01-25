package bg.sofia.uni.fmi.piss.project.tm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    String index() {
        return "index";
    }

    @GetMapping("/registrationForm")
    String registrationForm() {
        return "registrationForm";
    }

    @GetMapping("/loginForm")
    String loginForm() {
        return "loginForm";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    @GetMapping("/users")
    String users() {
        return "users";
    }

    @GetMapping("/userCourses")
    String userCourses() {
        return "user-courses.html";
    }

    @GetMapping("/settings")
    String settings() { return "settings.html"; }

    @GetMapping("/courses")
    String courses() {
        return "courses";
    }
}
