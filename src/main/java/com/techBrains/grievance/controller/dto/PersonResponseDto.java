package com.techBrains.grievance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponseDto {

    private String village;
    private String mandal;
    private String personName;
    private String phone;
}
