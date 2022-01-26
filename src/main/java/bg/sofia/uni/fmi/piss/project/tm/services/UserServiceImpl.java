package bg.sofia.uni.fmi.piss.project.tm.services;

import bg.sofia.uni.fmi.piss.project.tm.services.interfaces.UserService;
import bg.sofia.uni.fmi.piss.project.tm.utils.EntityToDtoMapper;
import bg.sofia.uni.fmi.piss.project.tm.dtos.ImageDto;
import bg.sofia.uni.fmi.piss.project.tm.dtos.TeachSmartUserDto;
import bg.sofia.uni.fmi.piss.project.tm.models.TeachSmartUser;
import bg.sofia.uni.fmi.piss.project.tm.repositories.TeachSmartUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bg.sofia.uni.fmi.piss.project.tm.utils.SecurityConstants.USER_DIR;

@Service
public class UserServiceImpl implements UserService {

    private TeachSmartUserRepository userRepository;

    private UserAssembler userAssembler;

    @Autowired
    public UserServiceImpl(TeachSmartUserRepository userRepository, UserAssembler userAssembler) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
    }

    @Override
    public ResponseEntity<TeachSmartUserDto> register(TeachSmartUserDto userDto) {
        Optional<TeachSmartUser> existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        TeachSmartUser user = userAssembler.toUser(userDto);

        try {

            Path path = Paths.get(USER_DIR);
            Files.createDirectories(path);
            System.out.println("Directory is created!");

        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }

        userRepository.save(user);
        return new ResponseEntity<>(EntityToDtoMapper.toUserDto(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity login(TeachSmartUserDto userDto) {
        Optional<TeachSmartUser> user = userRepository.findByUsername(userDto.getUsername());
        if (!user.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TeachSmartUserDto> getAuthUser(String username) {
        Optional<TeachSmartUser> user = userRepository.findByUsername(username);

        return user.map(teachSmartUser -> new ResponseEntity<>(EntityToDtoMapper.toUserDto(teachSmartUser), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Override
    public ResponseEntity getAllUsers() {
        List<TeachSmartUser> allUsers = userRepository.findAll();

        if (allUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allUsers
            .stream()
            .map(EntityToDtoMapper::toUserDto)
            .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAuthUserProfilePic(String username) {

        ImageDto image;

        try {
            image = new ImageDto(USER_DIR + username + File.separator + "profile_pic.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}

