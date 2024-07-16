package com.techBrains.grievance.service;

import com.techBrains.grievance.controller.dto.LoginRequestDto;
import com.techBrains.grievance.controller.dto.LoginResponseDto;
import com.techBrains.grievance.exception.ResourceNotFoundException;
import com.techBrains.grievance.repository.UserRepository;
import com.techBrains.grievance.repository.document.UserDocument;
import com.techBrains.grievance.util.GrievanceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
@Slf4j
public class AdminService {

    @Autowired
    UserRepository userRepository;

    public LoginResponseDto loginUser(LoginRequestDto requestDto) {

        Optional<UserDocument> optionalUserDocument =
                userRepository.findByLoginIdAndPassword(requestDto.getLoginId(), requestDto.getPassword());
        LoginResponseDto responseDto = new LoginResponseDto();

        if (optionalUserDocument.isPresent()) {
            log.info("User Document found in DB for loginId : {}", requestDto.getLoginId());
            GrievanceUtil.copyProperties(responseDto, optionalUserDocument.get());
        } else {
            log.info("User Document not found in DB for loginId : {}", requestDto.getLoginId());
            throw new ResourceNotFoundException("Invalid User Name Or Password");
        }
        return responseDto;

    }
}
