package com.company.journal.App.services;

import com.company.journal.App.entity.JournalEntry;
import com.company.journal.App.entity.User;
import com.company.journal.App.repository.JournalEntryRepository;
import com.company.journal.App.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder   = new BCryptPasswordEncoder();

    public void saveEntry(User user){

        userRepository.save(user);
    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getEntryById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }

//    public JournalEntry updateById(ObjectId id, JournalEntry journalEntry) {
//        if (!journalEntryRepository.existsById(id)) {
//            // This automatically returns a 404 HTTP status to the frontend
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal entry not found with id: " + id);
//        }
//        journalEntry.setId(id);
//        return journalEntryRepository.save(journalEntry);
//    }
}
