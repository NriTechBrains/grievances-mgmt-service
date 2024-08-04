package com.techBrains.grievance.service.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageData {

    private String message;
    private Set<String> phones;
    private String sourceLanguage;
    private String targetLanguage;
}
