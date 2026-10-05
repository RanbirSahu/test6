package com.restapi.service;

import com.restapi.entity.Registration;
import com.restapi.exception.ResourceNotFound;
import com.restapi.payload.RegistrationDto;
import com.restapi.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;


    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public RegistrationDto addRegistration(RegistrationDto registrationDto) {

      /* Registration reg=new Registration();
       reg.setEmailId(registrationDto.getEmailId());
       reg.setName(registrationDto.getName());
       reg.setMobile(registrationDto.getMobile());
       registrationRepository.save(reg);*/
        //dto to entity
        Registration registration = convertDtoToEntity(registrationDto);
        registrationRepository.save(registration);
        //entity to dto
        RegistrationDto registrationDto1 = convertEntityToDto(registration);
        return registrationDto1;


    }

    public void deleteRegistraion(long id) {
        registrationRepository.deleteById(id);

    }

    public void deleteRegistrationByPathParam(long id) {
        registrationRepository.deleteById(id);
    }

    public void updateRegistration(long id, Registration registration) {
        //actual data coming from database

        Optional<Registration> opUser = registrationRepository.findById(id);
        if(opUser.isPresent()){
            Registration reg = opUser.get();
            reg.setName(registration.getName());
            reg.setMobile(registration.getMobile());
            reg.setEmailId(registration.getEmailId());
            registrationRepository.save(reg);
        }
    }

    public List<RegistrationDto> getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir) {

                Sort sort=sortDir.equalsIgnoreCase("asc") ?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
               Pageable pages=PageRequest.of(pageNo, pageSize, sort);

              Page<Registration> records = registrationRepository.findAll(pages); //here we give the
             List<Registration> registrations = records.getContent();
                System.out.println(pages.getPageNumber());
                System.out.println(pages.getPageSize());
                System.out.println(records.getTotalPages());
                System.out.println(records.getTotalElements());
                System.out.println(records.isFirst());
                System.out.println(records.isLast());
                System.out.println(records.getNumber());
        List<RegistrationDto> regDtos = registrations.stream().map(this::convertEntityToDto).collect(Collectors.toList());

        return regDtos;

    }

    public Registration getRegistrationById(long id) {
       return  registrationRepository.findById(id).orElseThrow(
               ()->new ResourceNotFound("Registration not found with id: " + id));

    }

    public RegistrationDto convertEntityToDto(Registration registration){
        RegistrationDto registrationDto = modelMapper.map(registration, RegistrationDto.class);
        return registrationDto;
    }
    public Registration convertDtoToEntity(RegistrationDto registrationDto){
        Registration registration = modelMapper.map(registrationDto, Registration.class);
        return registration;
    }


}
