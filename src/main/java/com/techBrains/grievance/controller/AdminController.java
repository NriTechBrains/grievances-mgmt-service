package com.techBrains.grievance.controller;

import com.techBrains.grievance.controller.dto.LoginRequestDto;
import com.techBrains.grievance.controller.dto.LoginResponseDto;
import com.techBrains.grievance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/v1")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto requestDto) {

        LoginResponseDto loginResponseDto = adminService.loginUser(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
    }
}
