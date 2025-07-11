package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService UserService;

    @GetMapping("/health_check")
    public String healthCheck(){
        return "ok";
    }


    @PostMapping("/createUser")
    public boolean UserEntry(@RequestBody User user){
        return UserService.saveEntry(user);
    }

}
