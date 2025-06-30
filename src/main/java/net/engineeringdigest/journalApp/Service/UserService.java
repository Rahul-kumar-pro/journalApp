package net.engineeringdigest.journalApp.Service;


import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j

public class UserService {

@Autowired
private UserRepository UserRepository;



private static final BCryptPasswordEncoder encoderPassword=new BCryptPasswordEncoder();

public boolean saveEntry(User user){
   try{
       user.setPassword(encoderPassword.encode(user.getPassword()));
       user.setRoles(Arrays.asList("USER"));
       UserRepository.save(user);
       return true;
    }catch(Exception e){
       log.error("Error occurred for {} :",user.getUsername(),e);
    return false;

    }
   }
    public void saveAdmin(User user){
        user.setPassword(encoderPassword.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        UserRepository.save(user);
    }

    public void saveUser(User user){
          UserRepository.save(user);
    }

 public List<User>getAll(){
     return UserRepository.findAll();
 }

  public Optional<User> findById(ObjectId id){
   return UserRepository.findById(id);
  }

public void deletebyid(ObjectId id){
    UserRepository.deleteById(id);
}


public User findByusername(String username){
     return UserRepository.findByusername(username);

}
}
//Controller------>Service------->Repository