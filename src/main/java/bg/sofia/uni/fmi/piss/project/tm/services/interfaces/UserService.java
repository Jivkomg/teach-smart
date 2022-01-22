package bg.sofia.uni.fmi.piss.project.tm.services.interfaces;

import bg.sofia.uni.fmi.piss.project.tm.dtos.TeachSmartUserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<TeachSmartUserDto> register(TeachSmartUserDto userDto);

  ResponseEntity login(TeachSmartUserDto userDto);

  ResponseEntity<TeachSmartUserDto> getAuthUser(String username);

  ResponseEntity getAuthUserProfilePic(String username);

  ResponseEntity getAllUsers();
}
