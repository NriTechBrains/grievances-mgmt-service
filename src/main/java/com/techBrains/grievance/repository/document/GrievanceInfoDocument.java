package com.techBrains.grievance.repository.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(value = "grievanceInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GrievanceInfoDocument {

    @Id
    private String id;
    @Indexed(unique=true)
    private String grievanceId;
    private String name;
    private String villageOrTown;
    private String mandal;
    private String grievanceDesc;
    private String department;
    private String assemblyName;
    private int assemblyNumber;
    private String phoneNumber;
    private String personStatus; //need to change this
    private String departmentStatus;
    private String assigneeName;
    private String assigneePhone;
    private String dueDate;
    private String departmentName;
    private String departmentCode;
    private String sla;
    private String assignedTo;
    private boolean assignToMe;
    private String loginUserId;
    @CreatedDate
    private Instant createdDateTime;
    @LastModifiedDate
    private Instant updatedDateTime;
}
