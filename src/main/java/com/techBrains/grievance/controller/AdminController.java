package com.techBrains.grievance.controller;

import com.techBrains.grievance.controller.dto.LoginRequestDto;
import com.techBrains.grievance.controller.dto.LoginResponseDto;
import com.techBrains.grievance.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/v1")
@Slf4j
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(value = "/login",
            consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto requestDto) {

        log.info("Login request for received {}", requestDto.getEmail());

        LoginResponseDto loginResponseDto = adminService.loginUser(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
    }
}
