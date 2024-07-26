package com.techBrains.grievance.service.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WhatsappResponse {

    private String status;
    private String errormsg;
    private int statuscode;
    private String requestid;
    private int msgcount;
    private int msgcost;

}
