package com.techBrains.grievance.service;

import com.techBrains.grievance.controller.dto.*;
import com.techBrains.grievance.repository.*;
import com.techBrains.grievance.repository.document.*;
import com.techBrains.grievance.exception.ResourceNotFoundException;
import com.techBrains.grievance.service.data.MessageData;
import com.techBrains.grievance.util.GrievanceUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
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

    @Autowired
    MessageSenderService messageSenderService;

    @Autowired
    VillageDeptContactDetailsRepository villageDeptContactDetailsRepository;

    @Autowired
    PeopleRepresentativeDetailsRepository peopleRepresentativeDetailsRepository;

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

    //TODO: identify this is new person, if it is then create into personDetails document
    public GrievanceInfoResponseDto createGrievance(GrievanceInfoRequestDto requestDto) {

        GrievanceInfoDocument grievanceInfoDocument = new GrievanceInfoDocument();

        GrievanceUtil.copyProperties(grievanceInfoDocument, requestDto);
        grievanceInfoDocument.setGrievanceId(generateGrievanceId(requestDto.getAssemblyNumber(),
                requestDto.getDepartmentCode()));
        grievanceInfoDocument.setStatus(GrievancesStatusEnum.CREATE.name());

        GrievanceInfoResponseDto responseDto = new GrievanceInfoResponseDto();

        grievanceInfoDocument = repository.save(grievanceInfoDocument);


        Set<String> uniquePhoneNumbers = new HashSet<>();

        JSONObject departmentContactDetails = new JSONObject(requestDto.getDepartmentContactDetails());
        JSONArray userDetails = departmentContactDetails.getJSONArray("userDetails");

        extractPhoneNumbersFromMap(userDetails.toList(), uniquePhoneNumbers);

        JSONObject politicalContactDetails = new JSONObject(requestDto.getPoliticalContactDetails());
        userDetails = politicalContactDetails.getJSONArray("userDetails");
        extractPhoneNumbersFromMap(userDetails.toList(), uniquePhoneNumbers);

        MessageData messageData = MessageData.builder()
                .message(requestDto.getGrievanceDesc())
                .phones(uniquePhoneNumbers)
                .targetLanguage("te")
                .sourceLanguage("en")
                .build();

        messageSenderService.sendMessage(messageData);

        GrievanceUtil.copyProperties(responseDto, grievanceInfoDocument);

        if(requestDto.isNewPerson()) {
            personRepository.save(PersonDetailsDocument.builder()
                    .phone(requestDto.getPhoneNumber())
                    .personName(requestDto.getName())
                    .village(requestDto.getVillageOrTown())
                    .mandal(requestDto.getMandal())
                    .build());
        }

        return responseDto;

    }

    private static void extractPhoneNumbersFromMap(List<Object> list, Set<String> phoneNumbers) {
        for (Object obj : list) {
            if (obj instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) obj;
                if (map.containsKey("data")) {
                    Map<String, Object> data = (Map<String, Object>) map.get("data");
                    if (data.containsKey("phone")) {
                        phoneNumbers.add(data.get("phone").toString());
                    }
                }
                if (map.containsKey("children")) {
                    extractPhoneNumbersFromMap((List<Object>) map.get("children"), phoneNumbers);
                }
            }
        }
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

    public List<PersonResponseDto> getPersonDetails(String phone) {

        List<PersonDetailsDocument> personDetailsDocumentList = personRepository.findByPhone(phone);
        List<PersonResponseDto> dtoList = new ArrayList<>();

        if(personDetailsDocumentList.isEmpty()) {
            log.info("Person Details Not Found in DB by phone : {}",phone);
            throw new ResourceNotFoundException("Person Details Not Found");
        } else {
            log.info("Person Details Found in DB by phone : {}",phone);
            personDetailsDocumentList.forEach(personDetailsDocument -> {
                PersonResponseDto personResponseDto = new PersonResponseDto();
                GrievanceUtil.copyProperties(personResponseDto, personDetailsDocument);
                dtoList.add(personResponseDto);
            });

            return dtoList;
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

    public VillageDeptContactDetailsResponseDto getDepartmentContactDetails(String departmentId,
                                                                  String mandal, String village) {

        VillageDeptContactDetailsResponseDto responseDto = new VillageDeptContactDetailsResponseDto();

        Optional<VillageDeptContactDetailsDocument> document
                = villageDeptContactDetailsRepository.findByDepartmentIdAndMandalAndVillage(departmentId,
                mandal, village);

        document.ifPresent(villageDeptContactDetailsDocument -> GrievanceUtil.copyProperties(responseDto, villageDeptContactDetailsDocument));

        log.info ("{} -{}-{}", departmentId, mandal, village);

        return responseDto;
    }

    public GrievanceInfoResponseDto getGrievanceDetails(String grievanceId) {

        Optional<GrievanceInfoDocument> grievanceInfoDocumentOpt = repository.findByGrievanceId(grievanceId);

        GrievanceInfoResponseDto responseDto = new GrievanceInfoResponseDto();

        if(grievanceInfoDocumentOpt.isPresent()) {
            GrievanceUtil.copyProperties(responseDto, grievanceInfoDocumentOpt.get());
            return responseDto;
        } else {
            throw new ResourceNotFoundException("Grievance details not found!!!");
        }
    }

    public PeopleRepresentativeDetailsDto getPoliticalContactDetails(String mandal, String village) {
        PeopleRepresentativeDetailsDto dto = new PeopleRepresentativeDetailsDto();

        Optional<PeopleRepresentativeDetailsDocument> document =
                peopleRepresentativeDetailsRepository.findByMandalAndVillage(mandal, village);

        document.ifPresent(peopleRepresentativeDetailsDocument -> GrievanceUtil.copyProperties(dto, peopleRepresentativeDetailsDocument));

        return dto;
    }
}
