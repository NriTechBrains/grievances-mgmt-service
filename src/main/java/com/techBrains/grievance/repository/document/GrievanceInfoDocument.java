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
import java.util.List;
import java.util.Map;

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
    private String status;
    private String departmentStatus;
    private String name;
    private String villageOrTown;
    private String mandal;
    private String grievanceDesc;
    private String departmentCode;
    private String departmentId;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String dueDate;
    private String assemblyName;
    private int assemblyNumber;
    private String phoneNumber;
    private boolean assignToMe;
    private String loginUserId;
    private boolean newPerson;
    private Map<String, Object> departmentContactDetails;
    private Map<String, Object> politicalContactDetails;
    @CreatedDate
    private Instant createdDateTime;
    @LastModifiedDate
    private Instant updatedDateTime;
}
