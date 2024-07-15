package com.techBrains.grievance.repository;

import com.techBrains.grievance.repository.document.PersonDetailsDocument;
import com.techBrains.grievance.repository.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonRepository extends MongoRepository<PersonDetailsDocument, Integer> {

    Optional<PersonDetailsDocument> findByPhone(String phone);
}
