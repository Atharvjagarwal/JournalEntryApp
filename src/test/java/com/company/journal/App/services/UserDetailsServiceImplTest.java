package com.company.journal.App.services;

import com.company.journal.App.entity.User;
import com.company.journal.App.repository.UserRepository;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    public void loadUSerByUsername(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ram").password("123").build());
        UserDetails user = userDetailsService.loadUserByUsername("Ram");
    }
}
