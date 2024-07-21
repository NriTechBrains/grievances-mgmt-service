package com.techBrains.grievance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {

    private String loginId;
    private String userName;
    private String assemblyName;
    private String assemblyNumber;
    private String phone;
    private String email;
}
