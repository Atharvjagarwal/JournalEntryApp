package com.company.journal.App.controllers;

import com.company.journal.App.entity.User;
import com.company.journal.App.repository.UserRepository;
import com.company.journal.App.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public User getUserByUserName(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUserName(userName);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userInDB = userService.findByUserName(userName);
        if (userInDB != null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveUser(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        userRepository.deleteByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
