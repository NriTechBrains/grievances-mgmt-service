package com.techBrains.grievance.service;

import com.techBrains.grievance.controller.dto.LoginRequestDto;
import com.techBrains.grievance.controller.dto.LoginResponseDto;
import com.techBrains.grievance.repository.UserRepository;
import com.techBrains.grievance.repository.document.UserDocument;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    public LoginResponseDto loginUser(LoginRequestDto requestDto) {
        Optional<UserDocument> optionalUserDocument =
                userRepository.findByLoginIdAndPassword(requestDto.getLoginId(), requestDto.getPassword());
        LoginResponseDto responseDto = new LoginResponseDto();

        if (optionalUserDocument.isPresent()) {
            try {
                BeanUtils.copyProperties(responseDto, optionalUserDocument.get());
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return responseDto;

    }
}
