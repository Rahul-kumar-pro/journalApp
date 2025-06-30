package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,ObjectId> {

    User findByusername(String username);

    void deleteByUsername(String username);
}







