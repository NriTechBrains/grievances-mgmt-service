package com.techBrains.grievance.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GrievanceInfoResponseDto {

    private String id;
    private String GrievanceId;
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
    private String status;
    private String departmentStatus;
    private Instant createdDateTime;
    private Instant updatedDateTime;
}
