package com.techBrains.grievance.util;

import java.time.Instant;

public class GrievanceUtil {
    public static String generateGrievanceId(int assemblyNumber, String departmentCode) {

        return String.valueOf(assemblyNumber).concat("-")
                .concat(departmentCode).concat("-")
                .concat(String.valueOf(Instant.now().toEpochMilli()));
    }
}
