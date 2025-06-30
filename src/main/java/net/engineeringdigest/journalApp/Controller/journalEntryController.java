package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.Service.journalEntryService;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class journalEntryController {

    @Autowired
    public journalEntryService journalEntryService;
    @Autowired
    public UserService userService;

  @GetMapping
  public ResponseEntity<?> getAlljournalEntriesOfUser(){
      Authentication authenticatio= SecurityContextHolder.getContext().getAuthentication();
      String username= authenticatio.getName();
      User user=userService.findByusername(username);
      List<journalEntry>All=user.getJournalEntrie();
      if(All!=null && !All.isEmpty()){
          return new ResponseEntity<>(All,HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping
  public ResponseEntity<?> createEntry(@RequestBody journalEntry myEntry){
      try{
          Authentication authenticatio1= SecurityContextHolder.getContext().getAuthentication();
          String username= authenticatio1.getName();
          journalEntryService.saveEntry(myEntry,username);
          return new ResponseEntity<>(myEntry, HttpStatus.CREATED);}
      catch(Exception e){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
  }

  @GetMapping("id/{myid}")
  public ResponseEntity<journalEntry> getbyid(@PathVariable ObjectId myid){
//     Optional<journalEntry> journalEntry1=journalEntryService.findById(myid);
      Authentication authenticatio1= SecurityContextHolder.getContext().getAuthentication();
      String username= authenticatio1.getName();
      User user=userService.findByusername(username);
      List<journalEntry>collect=user.getJournalEntrie().stream()
              .filter(x-> x.getId().equals(myid)).collect(Collectors.toList());
      if (!collect.isEmpty()) {
          Optional<journalEntry>journalEntry=journalEntryService.findById(myid);
          if (journalEntry.isPresent()) {
              return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
          }
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }


  @DeleteMapping("id/{myid}")
  public ResponseEntity<?> Deletebyid(@PathVariable ObjectId myid){
      Authentication authenticatio1= SecurityContextHolder.getContext().getAuthentication();
      String username= authenticatio1.getName();
     boolean removed=journalEntryService.deletebyid(myid,username);
      if (removed){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }


    @PutMapping("id/{myid}")
    public ResponseEntity<?> updatebyid(@PathVariable ObjectId myid,@RequestBody journalEntry newEntry){

        Authentication authenticatio1= SecurityContextHolder.getContext().getAuthentication();
        String username= authenticatio1.getName();
        User user=userService.findByusername(username);
        List<journalEntry>collect=user.getJournalEntrie().stream()
                .filter(x-> x.getId().equals(myid)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<journalEntry>journalEntry=journalEntryService.findById(myid);
            if (journalEntry.isPresent()) {
                journalEntry old=journalEntry.get();
                old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() :old.getTitle());
                old.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ? newEntry.getContent(): old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
