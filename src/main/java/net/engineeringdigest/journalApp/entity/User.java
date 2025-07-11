package net.engineeringdigest.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document(collection="User")
@Data
@Builder
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique=true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    @DBRef
    private List<journalEntry> journalEntrie=new ArrayList<>();
    private List<String>roles;
    
}
