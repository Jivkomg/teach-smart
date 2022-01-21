package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.WevipUserDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.WevipUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

  @Autowired
  private PasswordEncoder passwordEncoder;

  WevipUser toUser(WevipUserDto userDto) {
    return new WevipUser(userDto.getUsername(),
            userDto.getEmail(),
            passwordEncoder.encode(userDto.getPassword()));
  }

  WevipUserDto toUserDto(WevipUser user) {
    WevipUserDto userDto = new WevipUserDto();
    userDto.setUserId(user.getId());
    userDto.setName(user.getUsername());
    userDto.setEmail(user.getEmail());
    return userDto;
  }
}
