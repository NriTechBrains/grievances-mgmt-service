package com.techBrains.grievance.repository;

import com.techBrains.grievance.repository.document.GrievanceInfoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GrievanceMgmtRepository extends MongoRepository<GrievanceInfoDocument, Integer> {
    Optional<GrievanceInfoDocument> findByGrievanceId(String grievanceId);
}
