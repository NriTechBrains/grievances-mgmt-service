package com.techBrains.grievance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VillageDeptContactDetailsResponseDto {

    private String departmentId;
    private String mandal;
    private String village;
    private List<Map<String, Object>> contactDetails;
}
