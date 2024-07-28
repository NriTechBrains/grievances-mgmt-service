package com.techBrains.grievance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    /*private String departmentName;
    private String sla;*/
    private String departmentCode;
    private String departmentId;
    private String departmentPerson;
    private String departmentContact;
    private String dueDate;
    private String assemblyName;
    private int assemblyNumber;
    private String phoneNumber;
    private boolean assignToMe;
    private String loginUserId;
    private boolean newPerson;
    private String status;
    private String departmentStatus;
}
