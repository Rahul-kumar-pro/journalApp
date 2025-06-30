package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/All-Users")
    public ResponseEntity<?> getAllUsers() {
        List<User> All = userService.getAll();
        if (All != null && !All.isEmpty()) {
            return new ResponseEntity<>(All, HttpStatus.OK);
        }
        return new ResponseEntity<>(All, HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-user-admin")
    public void Create_User(@RequestBody User user){
        userService.saveAdmin(user);
    }
}


