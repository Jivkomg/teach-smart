package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.WevipUserDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.WevipUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserAssemblerTest {
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserAssembler assembler;

    @Test
    public void toUser_PasswordEncoded(){
        String encodedPass = "encoded-pass";
        WevipUserDto dto = new WevipUserDto();
        dto.setName("username");
        dto.setEmail("email");
        dto.setPassword("password");
        Mockito.when(encoder.encode(dto.getPassword())).thenReturn(encodedPass);

        WevipUser user = assembler.toUser(dto);
        Assert.assertEquals(encodedPass,user.getPassword());
    }
}
