package com.techBrains.grievance.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class GrievanceInfoRequestDto {

    private String name;
    private String villageOrTown;
    private String mandal;
    private String grievanceDesc;
    private String departmentName;
    private String departmentCode;
    private String sla;
    private String assignedTo;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String dueDate;

    private String assemblyName;
    private int assemblyNumber;
    private String phoneNumber;
    private boolean assignToMe;
    private String loginUserId;
}
