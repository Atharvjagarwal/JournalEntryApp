package com.company.journal.App.services;

import com.company.journal.App.entity.User;
import com.company.journal.App.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(userArgumentsProvider.class)
    public void testSaveNewUser(User user){
        Assertions.assertTrue(userService.saveNewUser(user));
    }
    
    

    @ParameterizedTest
    @ValueSource(strings = {
            "Atharv",
            "Kilvish"
    })
    public void testFindByUserName(String name){
        Assertions.assertNotNull(userRepository.findByUserName(name));
    }
    @Test
    public void testgetAll(){
        Assertions.assertNotNull(userRepository.findAll());
    }

    @ParameterizedTest
    @CsvSource({
            "2,3,5",
            "5,5,10",
            "5,4,3"
    })
    public void test(int a, int b, int expected){
        Assertions.assertEquals(expected, a+b);
    }
}
