package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.TeachSmartUserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<TeachSmartUserDto> register(TeachSmartUserDto userDto);

  ResponseEntity login(TeachSmartUserDto userDto);

  ResponseEntity<TeachSmartUserDto> getAuthUser(String username);

  ResponseEntity getAuthUserProfilePic(String username);

  ResponseEntity getAllUsers();
}
