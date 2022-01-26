package bg.sofia.uni.fmi.piss.project.tm.controllers;

import bg.sofia.uni.fmi.piss.project.tm.dtos.TeachSmartUserDto;

import javax.validation.Valid;

import bg.sofia.uni.fmi.piss.project.tm.services.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user", produces = "application/json", consumes = "application/json")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registrationForm")
    public ResponseEntity<TeachSmartUserDto> processRegisterUser(@Valid @RequestBody TeachSmartUserDto userDto, BindingResult binding) {
        if (binding.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return userService.register(userDto);
    }

    @PostMapping("/loginForm")
    public ResponseEntity processLoginUser(@Valid @RequestBody TeachSmartUserDto userDto, BindingResult binding) {
        if (binding.hasErrors()) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        return userService.login(userDto);
    }

    @GetMapping("/current/{username}")
    public ResponseEntity<TeachSmartUserDto> getCurrentUser(@PathVariable String username) {
        return userService.getAuthUser(username);
    }

    @GetMapping("/current/profile-pic/{username}")
    public ResponseEntity getCurrentUserProfilePic(@PathVariable String username) {
        return userService.getAuthUserProfilePic(username);
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        return userService.getAllUsers();
    }
}
