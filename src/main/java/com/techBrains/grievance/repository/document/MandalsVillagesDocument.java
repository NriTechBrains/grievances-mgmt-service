package com.techBrains.grievance.repository.document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "mandalsAndVillages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MandalsVillagesDocument {

    @Id
    private String id;
    private String mandal;
    private String village;
}
