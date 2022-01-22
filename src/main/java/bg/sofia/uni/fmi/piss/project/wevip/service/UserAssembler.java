package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.TeachSmartUserDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.TeachSmartUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class UserAssembler {

  @Autowired
  private PasswordEncoder passwordEncoder;

  TeachSmartUser toUser(TeachSmartUserDto userDto) {
    return new TeachSmartUser(userDto.getUsername(),
            userDto.getEmail(),
            passwordEncoder.encode(userDto.getPassword()));
  }
}
