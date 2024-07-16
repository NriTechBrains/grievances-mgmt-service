package com.techBrains.grievance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MandalsVillagesResponseDto {

    private Set<String> mandals;
    private Map<String, List<String>> villages;

}
