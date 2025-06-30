package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Disabled
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;



    @BeforeEach
//    @BeforeAll

//    @AfterAll
//    @AfterEach









//    @ValueSource(strings={
//            "Ram",
//            "Syam",
//            "pari","jallu"
//    })
    @ParameterizedTest
    @ArgumentsSource(UserArgumenetProvider.class)
    public void testSaveNewUsername(User name){
     assertTrue(userService.saveEntry(name));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "10,3,13",

    })
    public void test(int a,int b,int expected){
       assertEquals(expected,a+b);
    }
}
