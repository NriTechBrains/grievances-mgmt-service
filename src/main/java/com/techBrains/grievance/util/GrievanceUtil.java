package com.techBrains.grievance.util;

import com.techBrains.grievance.exception.CopyPropertiesException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;

public class GrievanceUtil {
    public static String generateGrievanceId(int assemblyNumber, String departmentCode) {

        return String.valueOf(assemblyNumber).concat("-")
                .concat(departmentCode).concat("-")
                .concat(String.valueOf(Instant.now().toEpochMilli()));
    }

    public static void copyProperties( Object destObject, Object srcObject) {

        try {
            BeanUtils.copyProperties(destObject, srcObject);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new CopyPropertiesException("BeanUtil CopyProperties Exception");
        }

    }
}
