package com.techBrains.grievance.service.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageData {

    private String message;
    private String phone;
    private String sourceLanguage;
    private String targetLanguage;
}
