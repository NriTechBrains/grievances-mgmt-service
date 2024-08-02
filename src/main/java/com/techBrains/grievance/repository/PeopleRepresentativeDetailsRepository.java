package com.techBrains.grievance.repository;

import com.techBrains.grievance.repository.document.PeopleRepresentativeDetailsDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PeopleRepresentativeDetailsRepository extends MongoRepository<PeopleRepresentativeDetailsDocument, Integer> {
    Optional<PeopleRepresentativeDetailsDocument> findByMandalAndVillage(String mandal, String village);
}
