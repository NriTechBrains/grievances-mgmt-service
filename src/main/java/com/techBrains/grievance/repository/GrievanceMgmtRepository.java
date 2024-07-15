package com.techBrains.grievance.repository;

import com.techBrains.grievance.repository.document.GrievanceInfoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GrievanceMgmtRepository extends MongoRepository<GrievanceInfoDocument, Integer> {
}
