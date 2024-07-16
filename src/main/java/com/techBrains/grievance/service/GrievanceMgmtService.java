package com.techBrains.grievance.service;

import com.techBrains.grievance.controller.dto.*;
import com.techBrains.grievance.repository.GrievanceMgmtRepository;
import com.techBrains.grievance.repository.DepartmentRepository;
import com.techBrains.grievance.repository.MandalsVillagesRepository;
import com.techBrains.grievance.repository.PersonRepository;
import com.techBrains.grievance.repository.document.GrievanceInfoDocument;
import com.techBrains.grievance.repository.document.DepartmentDocument;
import com.techBrains.grievance.repository.document.MandalsVillagesDocument;
import com.techBrains.grievance.repository.document.PersonDetailsDocument;
import com.techBrains.grievance.exception.ResourceNotFoundException;
import com.techBrains.grievance.util.GrievanceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.techBrains.grievance.util.GrievanceUtil.generateGrievanceId;
import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class GrievanceMgmtService {

    @Autowired
    GrievanceMgmtRepository repository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MandalsVillagesRepository mandalsVillagesRepository;

    public List<GrievanceInfoResponseDto> getAllGrievances() {

        List<GrievanceInfoResponseDto> dtoList = new ArrayList<>();

        List<GrievanceInfoDocument> grievanceInfoDocuments = repository.findAll();

        grievanceInfoDocuments.forEach(grievanceInfoDocument -> {
            GrievanceInfoResponseDto grievanceInfoDto = new GrievanceInfoResponseDto();
            GrievanceUtil.copyProperties(grievanceInfoDto, grievanceInfoDocument);
            dtoList.add(grievanceInfoDto);
        });

        return dtoList;
    }

    //Need to create persons document
    public GrievanceInfoResponseDto createGrievance(GrievanceInfoRequestDto requestDto) {

        GrievanceInfoDocument grievanceInfoDocument = new GrievanceInfoDocument();

        GrievanceUtil.copyProperties(grievanceInfoDocument, requestDto);
        grievanceInfoDocument.setGrievanceId(generateGrievanceId(requestDto.getAssemblyNumber(),
                requestDto.getDepartmentCode()));

        GrievanceInfoResponseDto responseDto = new GrievanceInfoResponseDto();

        grievanceInfoDocument = repository.save(grievanceInfoDocument);
        System.out.println(grievanceInfoDocument);

        GrievanceUtil.copyProperties(responseDto, grievanceInfoDocument);

        System.out.println(responseDto);
        return responseDto;

    }

    public List<DepartmentResponseDto> getDepartments() {
        List<DepartmentDocument> departmentDocuments = departmentRepository.findAll();

        List<DepartmentResponseDto> departmentResponseDtos = new ArrayList<>();

        departmentDocuments.forEach(departmentDocument -> {
            DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
            GrievanceUtil.copyProperties(departmentResponseDto, departmentDocument);
            departmentResponseDtos.add(departmentResponseDto);

        });
        return departmentResponseDtos;
    }

    public Optional<PersonResponseDto> getPersonDetails(String phone) {

        Optional<PersonDetailsDocument> personDetailsDocumentOpt = personRepository.findByPhone(phone);
        PersonResponseDto personResponseDto = new PersonResponseDto();

        if(personDetailsDocumentOpt.isPresent()) {
            log.info("Person Details Found in DB by phone : {}",phone);
            GrievanceUtil.copyProperties(personResponseDto, personDetailsDocumentOpt.get());
            return Optional.of(personResponseDto);
        } else {
            log.info("Person Details Not Found in DB by phone : {}",phone);
            throw new ResourceNotFoundException("Person Details Not Found");
        }
    }

    public MandalsVillagesResponseDto getMandalsAndVillages() {

        List<MandalsVillagesDocument> documents = mandalsVillagesRepository.findAll();

        Map<String, Set<String>> map =
                documents.stream().collect(groupingBy(MandalsVillagesDocument :: getMandal,
                        Collectors.mapping(MandalsVillagesDocument::getVillage,
                        Collectors.toSet())));

        MandalsVillagesResponseDto responseDto = new MandalsVillagesResponseDto();
        responseDto.setMandals(map.keySet());
        responseDto.setVillages(map);

        return responseDto;
    }
}
