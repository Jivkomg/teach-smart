package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.WevipUserDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.WevipUser;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<WevipUserDto> register(WevipUserDto userDto);

  ResponseEntity login(WevipUserDto userDto);

  ResponseEntity<WevipUserDto> getAuthUser(String username);

  ResponseEntity getAuthUserProfilePic(String username);

  ResponseEntity getAllUsers();
}
