package com.techBrains.grievance.repository.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "personDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDetailsDocument {
    @Id
    private String id;
    private String personName;
    private String village;
    private String mandal;
    private String phone;
}
