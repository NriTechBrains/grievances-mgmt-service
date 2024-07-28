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
    private String assemblyName;
    private int assemblyNumber;
    private String phoneNumber;
    private String status;
    private String departmentStatus;
    private String departmentPerson;
    private String departmentContact;
    private String dueDate;
   /* private String departmentName;
    private String sla;*/
    private String departmentCode;
    private String departmentId;
    private boolean assignToMe;
    private String loginUserId;
    private boolean newPerson;
    @CreatedDate
    private Instant createdDateTime;
    @LastModifiedDate
    private Instant updatedDateTime;
}
