package com.techBrains.grievance.repository.document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(value = "villageDeptContactDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VillageDeptContactDetailsDocument {

    private String departmentId;
    private String mandal;
    private String village;
    private List<Map<String, Object>> contactDetails;
}
