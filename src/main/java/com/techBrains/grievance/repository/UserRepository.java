package com.techBrains.grievance.repository;

import com.techBrains.grievance.repository.document.PersonDetailsDocument;
import com.techBrains.grievance.repository.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, Integer> {
    Optional<UserDocument> findByLoginIdAndPassword(String loginId, String password);


    Optional<UserDocument> findByEmailAndPassword(String email, String password);
}
