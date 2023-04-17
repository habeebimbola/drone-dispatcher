package com.musala.rest;

import com.musala.domain.dto.MedicationDto;
import com.musala.service.DroneDispatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medication")
public class MedicationController {

    @Autowired()
    private DroneDispatchService droneDispatchService;

    @PostMapping("/create")
    public ResponseEntity<MedicationDto> createMedication(@Valid() @RequestBody MedicationDto medicationDto, BindingResult bindingResult){

        if( bindingResult.hasErrors())
        {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MedicationDto savedMedication = this.droneDispatchService.createMedication(medicationDto);
        ResponseEntity<MedicationDto> responseEntity = new ResponseEntity<>(savedMedication, HttpStatus.CREATED);
        return responseEntity;
    }
}
