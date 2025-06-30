package net.engineeringdigest.journalApp.Service;


import net.engineeringdigest.journalApp.Repository.journalEntryRepository;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntry;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class journalEntryService {

    private static final Logger log = LoggerFactory.getLogger(journalEntryService.class);
    @Autowired
private journalEntryRepository journalEntryRepository;
@Autowired
private UserService userService;

 @Transactional
 public void saveEntry(journalEntry journalEntry, String username){
     try {
         journalEntry.setDate(LocalDateTime.now());
         User user = userService.findByusername(username);
         journalEntry saved = journalEntryRepository.save(journalEntry);
         user.getJournalEntrie().add(saved);
         userService.saveUser(user);
     }catch(Exception e){
         log.error("e: ", e);
     }

 }
    public void saveEntry(journalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

 public List<journalEntry>getAll(){
     return journalEntryRepository.findAll();
 }

  public Optional<journalEntry> findById(ObjectId id){
   return journalEntryRepository.findById(id);
  }

  @Transactional
public Boolean deletebyid(ObjectId id, String username){
     boolean removed=false;
     try{
    User user=userService.findByusername(username);
    boolean remove=user.getJournalEntrie().removeIf(x-> x.getId().equals(id));
    if (remove){
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }} catch (Exception e) {
         log.error("e: ", e);
         throw new RuntimeException("An Occurred error Deleting by my id"+e);
     }
     return removed;
}
}
//Controller------>Service------->Repository