package com.techBrains.grievance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VillageDeptContactDetailsResponseDto {
    private String personName;
    private String phone;
}
