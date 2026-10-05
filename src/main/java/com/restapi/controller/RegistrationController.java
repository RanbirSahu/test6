package com.restapi.controller;

import com.restapi.entity.Registration;
import com.restapi.payload.RegistrationDto;
import com.restapi.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
   // url:: http://localhost:8080/api/registration/add
    @PostMapping("/add")
    public ResponseEntity<RegistrationDto> addRegistration(@RequestBody RegistrationDto registrationDto){

        RegistrationDto dto=registrationService.addRegistration(registrationDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);



    }
    @DeleteMapping("/delete") // url:  http://localhost:8080/api/registration/delete?id=1
    public String deleteRegistration(@RequestParam long id){
        registrationService.deleteRegistraion(id);
        return "Deleted";
    }
    @DeleteMapping("/{id}") //http://localhost:8080/api/registration/1
    public String DeleteRegistrationByUsingPathParameter(@PathVariable long id){
        registrationService.deleteRegistrationByPathParam(id);
        return "Deleted Using Path Parameter";
    }
    @PutMapping("/{id}")
    public String updateRegistration(@PathVariable long id ,@RequestBody Registration registration){
        registrationService.updateRegistration(id,registration);
        return "updated SuccessFully";
    }
    @GetMapping // url:: //http://localhost:8080/api/registration?pageNo=0&pageSize=5&sortBy&sortDir=
    public ResponseEntity<List<RegistrationDto>> getAllRegistration(
            @RequestParam(defaultValue = "0",required = false) int pageNo,
            @RequestParam(defaultValue = "5",required = false) int pageSize,
            @RequestParam(defaultValue = "id",required = false) String sortBy,
             @RequestParam(defaultValue = "asc",required = false) String sortDir



    ){
        List<RegistrationDto>allRegistrationDtos=registrationService.getAllRegistration(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allRegistrationDtos,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Registration getRegistrationById(@PathVariable long id){
        Registration registration=registrationService.getRegistrationById(id);
        return registration;
    }




}
