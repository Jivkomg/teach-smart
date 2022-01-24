package bg.sofia.uni.fmi.piss.project.tm.services;

import bg.sofia.uni.fmi.piss.project.tm.dtos.TeachSmartUserDto;
import bg.sofia.uni.fmi.piss.project.tm.models.TeachSmartUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class UserAssembler {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserAssembler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    TeachSmartUser toUser(TeachSmartUserDto userDto) {
        return new TeachSmartUser(userDto.getUsername(),
            userDto.getEmail(),
            passwordEncoder.encode(userDto.getPassword()));
    }
}
