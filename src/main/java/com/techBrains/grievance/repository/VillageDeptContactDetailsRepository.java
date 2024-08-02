package com.techBrains.grievance.repository;

import com.techBrains.grievance.repository.document.UserDocument;
import com.techBrains.grievance.repository.document.VillageDeptContactDetailsDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VillageDeptContactDetailsRepository extends MongoRepository<VillageDeptContactDetailsDocument, Integer> {
    Optional<VillageDeptContactDetailsDocument> findByDepartmentIdAndMandalAndVillage(String departmentId, String mandal, String village);
}
