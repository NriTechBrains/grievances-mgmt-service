package com.techBrains.grievance.repository;

import com.techBrains.grievance.repository.document.DepartmentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<DepartmentDocument, Integer> {
}
