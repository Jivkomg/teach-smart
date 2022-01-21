package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.TeachSmartUserDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.TeachSmartUser;
import bg.sofia.uni.fmi.piss.project.wevip.repository.TeachSmartUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private TeachSmartUserRepository userRepository;

    @Mock
    private UserAssembler userAssembler;

    @InjectMocks
    private UserServiceImpl userService;

    private TeachSmartUserDto dto;

    private TeachSmartUser user;

    @Before
    public void setUp(){
        dto = new TeachSmartUserDto();
        dto.setUsername("username");

        user = new TeachSmartUser();
        user.setUsername("username");
    }

    @Test
    public void register_AlreadyExistingUser(){
        Mockito.when(userRepository.findByUsername(dto.getUsername())).
                thenReturn(new TeachSmartUser());
        ResponseEntity<TeachSmartUserDto> result = userService.register(dto);
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void register_UserCreatedSuccessfully(){
        Mockito.when(userRepository.findByUsername(dto.getUsername())).
                thenReturn(null);
        Mockito.when(userAssembler.toUser(dto)).thenReturn(user);
        Mockito.when(userAssembler.toUserDto(user)).thenReturn(dto);

        ResponseEntity<TeachSmartUserDto> result = userService.register(dto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(dto,result.getBody());
    }

    @Test
    public void login_UserNotFound(){
        ResponseEntity result = userService.login(dto);
        assertEquals(HttpStatus.BAD_REQUEST,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void login_UserFound(){
        Mockito.when(userRepository.findByUsername(dto.getUsername())).
                thenReturn(user);
        ResponseEntity<TeachSmartUserDto> result = userService.login(dto);
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getAuthUser_UserNotFound(){
        ResponseEntity<TeachSmartUserDto> result = userService.getAuthUser("username");
        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
    }

    @Test
    public void getAuthUser_UserFound(){
        Mockito.when(userRepository.findByUsername(user.getUsername())).
                thenReturn(user);
        Mockito.when(userAssembler.toUserDto(user)).thenReturn(dto);

        ResponseEntity<TeachSmartUserDto> result = userService.getAuthUser(user.getUsername());
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(dto,result.getBody());
    }
}
