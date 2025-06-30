package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Repository.UserRepository;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {

@Autowired
    private UserService UserService;

    @Autowired
    private UserRepository UserRepository;


 @PutMapping
 public ResponseEntity<?> updateuser(@RequestBody User user){
     Authentication authenticatio=SecurityContextHolder.getContext().getAuthentication();
     String username= authenticatio.getName();
    User UserNDB=UserService.findByusername(username);
  if(UserNDB!=null){
      UserNDB.setUsername(user.getUsername());
      UserNDB.setPassword(user.getPassword());
      UserService.saveEntry(UserNDB);
   }
  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
    @DeleteMapping
    public ResponseEntity<?> Deletebyid(){
        Authentication authenticatio=SecurityContextHolder.getContext().getAuthentication();
        UserRepository.deleteByUsername(authenticatio.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
