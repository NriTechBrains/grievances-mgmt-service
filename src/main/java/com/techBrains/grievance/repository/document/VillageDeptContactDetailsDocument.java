package com.techBrains.grievance.repository.document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "villageDeptContactDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VillageDeptContactDetailsDocument {

    private String departmentId;
    private String mandal;
    private String village;
    private String personName;
    private String phone;
}
