package com.techBrains.grievance.repository.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDocument {

    @Id
    private String id;
    private String userName;
    private String password;
    private String loginId;
    private String assemblyName;
    private int assemblyNumber;
    private String phone;
    private String email;

}
