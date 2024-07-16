package com.techBrains.grievance.repository;

import com.techBrains.grievance.repository.document.MandalsVillagesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MandalsVillagesRepository extends MongoRepository<MandalsVillagesDocument, Integer> {

}
