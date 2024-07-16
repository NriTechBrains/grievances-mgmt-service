package com.techBrains.grievance.controller;

import com.techBrains.grievance.controller.dto.*;
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

    @GetMapping(value = "/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to new World!!!!");
    }
    @GetMapping(value = "/grievances", produces = {"application/json"})
    public ResponseEntity<List<GrievanceInfoResponseDto>> getAllGrievances() {
        List<GrievanceInfoResponseDto> list = service.getAllGrievances();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping(value = "/create",
            consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<GrievanceInfoResponseDto> createGrievance(@RequestBody GrievanceInfoRequestDto requestDto) {

        GrievanceInfoResponseDto responseDto = service.createGrievance(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);
    }


    @GetMapping(value = "/departments", produces = {"application/json"})
    public ResponseEntity<List<DepartmentResponseDto>> getDepartments() {

        List<DepartmentResponseDto> departmentResponseDtos = service.getDepartments();

        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDtos);
    }

    @GetMapping(value = "/detailsByPhone/{phone}", produces = {"application/json"})
    public ResponseEntity<PersonResponseDto> getPersonDetailsByPhone(@PathVariable String phone) {

        Optional<PersonResponseDto> personResponseDto = service.getPersonDetails(phone);

        return personResponseDto.map(responseDto -> ResponseEntity.status(HttpStatus.OK).body(responseDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

    }

    @GetMapping(value = "/mandalsAndVillages", produces = {"application/json"})
    public ResponseEntity<MandalsVillagesResponseDto> getMandalsAndVillages() {

        MandalsVillagesResponseDto responseDto = service.getMandalsAndVillages();

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }
}
