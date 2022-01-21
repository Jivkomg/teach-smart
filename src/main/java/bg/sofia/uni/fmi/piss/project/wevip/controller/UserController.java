package bg.sofia.uni.fmi.piss.project.wevip.controller;

import bg.sofia.uni.fmi.piss.project.wevip.dto.WevipUserDto;

import javax.validation.Valid;

import bg.sofia.uni.fmi.piss.project.wevip.model.Event;
import bg.sofia.uni.fmi.piss.project.wevip.model.WevipUser;
import bg.sofia.uni.fmi.piss.project.wevip.service.UserService;
import bg.sofia.uni.fmi.piss.project.wevip.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/user", produces = "application/json", consumes = "application/json")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/registrationForm")
  public ResponseEntity<WevipUserDto> processRegisterUser(@Valid @RequestBody WevipUserDto userDto, BindingResult binding) {
    if (binding.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    return userService.register(userDto);
  }

  @PostMapping("/loginForm")
  public ResponseEntity processLoginUser(@Valid @RequestBody WevipUserDto userDto, BindingResult binding) {
    if (binding.hasErrors()) {
      return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    return userService.login(userDto);
  }

  @PostMapping("/current/{username}")
  public ResponseEntity<WevipUserDto> getCurrentUser(@PathVariable String username) {
    return userService.getAuthUser(username);
  }

  @PostMapping("/current/profile-pic/{username}")
  public ResponseEntity getCurrentUserProfilePic(@PathVariable String username) {
    return userService.getAuthUserProfilePic(username);
  }

  @PostMapping("/all")
  public ResponseEntity getAllUsers() {
    return userService.getAllUsers();
  }
}
