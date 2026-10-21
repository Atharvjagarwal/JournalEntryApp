package com.company.journal.App.services;

import com.company.journal.App.entity.JournalEntry;
import com.company.journal.App.entity.User;
import com.company.journal.App.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private  UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, @PathVariable String userName){
        try {
            User user = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry",e);
        }

    }
    public void saveEntry(JournalEntry journalEntry){
       journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting a entry", e);
        }
        return removed;
    }

    public Optional<JournalEntry> findById(ObjectId myId) {
        return journalEntryRepository.findById(myId);
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
