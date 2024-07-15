package com.techBrains.grievance.service;

import com.techBrains.grievance.controller.dto.GrievanceInfoRequestDto;
import com.techBrains.grievance.controller.dto.GrievanceInfoResponseDto;
import com.techBrains.grievance.controller.dto.DepartmentResponseDto;
import com.techBrains.grievance.controller.dto.PersonResponseDto;
import com.techBrains.grievance.repository.GrievanceMgmtRepository;
import com.techBrains.grievance.repository.DepartmentRepository;
import com.techBrains.grievance.repository.PersonRepository;
import com.techBrains.grievance.repository.document.GrievanceInfoDocument;
import com.techBrains.grievance.repository.document.DepartmentDocument;
import com.techBrains.grievance.repository.document.PersonDetailsDocument;
import com.techBrains.grievance.exception.ResourceNotFoundException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.techBrains.grievance.util.GrievanceUtil.generateGrievanceId;

@Service
public class GrievanceMgmtService {

    @Autowired
    GrievanceMgmtRepository repository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PersonRepository personRepository;

    public List<GrievanceInfoResponseDto> getAllGrievances() {

        List<GrievanceInfoResponseDto> dtoList = new ArrayList<>();

        List<GrievanceInfoDocument> grievanceInfoDocuments = repository.findAll();

        grievanceInfoDocuments.forEach(grievanceInfoDocument -> {
            GrievanceInfoResponseDto grievanceInfoDto = new GrievanceInfoResponseDto();

            try {
                BeanUtils.copyProperties(grievanceInfoDto, grievanceInfoDocument);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            dtoList.add(grievanceInfoDto);

        });

        return dtoList;
    }

    //Need to create persons document
    public GrievanceInfoResponseDto createGrievance(GrievanceInfoRequestDto requestDto) {

        GrievanceInfoDocument grievanceInfoDocument = new GrievanceInfoDocument();
        try {
            BeanUtils.copyProperties(grievanceInfoDocument, requestDto);
            grievanceInfoDocument.setGrievanceId(generateGrievanceId(requestDto.getAssemblyNumber(),
                    requestDto.getDepartmentCode()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        GrievanceInfoResponseDto responseDto = new GrievanceInfoResponseDto();

        grievanceInfoDocument = repository.save(grievanceInfoDocument);
        System.out.println(grievanceInfoDocument);

        try {
            BeanUtils.copyProperties(responseDto, grievanceInfoDocument);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        System.out.println(responseDto);
        return responseDto;

    }

    public List<DepartmentResponseDto> getDepartments() {
        List<DepartmentDocument> departmentDocuments = departmentRepository.findAll();

        List<DepartmentResponseDto> departmentResponseDtos = new ArrayList<>();

        departmentDocuments.forEach(departmentDocument -> {
            DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();

            try {
                BeanUtils.copyProperties(departmentResponseDto, departmentDocument);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            departmentResponseDtos.add(departmentResponseDto);

        });
        return departmentResponseDtos;
    }

    public Optional<PersonResponseDto> getPersonDetails(String phone) {

        Optional<PersonDetailsDocument> personDetailsDocumentOpt = personRepository.findByPhone(phone);
        PersonResponseDto personResponseDto = new PersonResponseDto();

        if(personDetailsDocumentOpt.isPresent()) {
            try {
                BeanUtils.copyProperties(personResponseDto, personDetailsDocumentOpt.get());
                return Optional.of(personResponseDto);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }else {
            throw new ResourceNotFoundException("Person Details Not Found");
        }
    }
}
