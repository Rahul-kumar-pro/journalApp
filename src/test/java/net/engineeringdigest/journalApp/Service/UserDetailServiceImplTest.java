package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@Disabled
public class UserDetailServiceImplTest {

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private  UserDetailServiceImpl userDetailService;

    @MockBean
    private UserRepository userRepository;


    @Test
    @Disabled
    public void loadUserName() {
        UserDetails user = userDetailService.loadUserByUsername("Ram");
        when(userRepository.findByusername(ArgumentMatchers.anyString())).thenReturn( net.engineeringdigest.journalApp.entity.User.builder().username("Ram").password("uafsduc").roles(new ArrayList<>()).build());
    Assertions.assertNotNull(user);
    }
}
