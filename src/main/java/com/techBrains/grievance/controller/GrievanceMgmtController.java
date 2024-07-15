package com.techBrains.grievance.controller;

import com.techBrains.grievance.controller.dto.GrievanceInfoRequestDto;
import com.techBrains.grievance.controller.dto.GrievanceInfoResponseDto;
import com.techBrains.grievance.controller.dto.DepartmentResponseDto;
import com.techBrains.grievance.controller.dto.PersonResponseDto;
import com.techBrains.grievance.service.GrievanceMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grievance/v1")
public class GrievanceMgmtController {

    @Autowired
    GrievanceMgmtService service;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to new World!!!!");
    }
    @GetMapping("/grievances")
    public ResponseEntity<List<GrievanceInfoResponseDto>> getAllGrievances() {
        List<GrievanceInfoResponseDto> list = service.getAllGrievances();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/create")
    public ResponseEntity<GrievanceInfoResponseDto> createGrievance(@RequestBody GrievanceInfoRequestDto requestDto) {

        GrievanceInfoResponseDto responseDto = service.createGrievance(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);
    }


    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentResponseDto>> getDepartments() {

        List<DepartmentResponseDto> departmentResponseDtos = service.getDepartments();

        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDtos);
    }

    @GetMapping("/detailsByPhone/{phone}")
    public ResponseEntity<PersonResponseDto> getPersonDetailsByPhone(@PathVariable String phone) {

        Optional<PersonResponseDto> personResponseDto = service.getPersonDetails(phone);

        return personResponseDto.map(responseDto -> ResponseEntity.status(HttpStatus.OK).body(responseDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

    }
}
