package com.musala.rest;

import com.musala.domain.dto.ApiResponse;
import com.musala.domain.dto.MedicationDto;
import com.musala.domain.dto.ResponseCode;
import com.musala.service.DroneDispatchService;
import com.musala.validation.MedicationErrorBuilder;
import com.musala.validation.MedicationValidationError;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("medication")
public class MedicationController {

    @Autowired()
    private DroneDispatchService droneDispatchService;

    @PostMapping("/create")
    public ResponseEntity<?> createMedication(@Valid() @RequestBody MedicationDto medicationDto, BindingResult bindingResult){

        if( bindingResult.hasErrors())
        {
            return ResponseEntity.badRequest().body(MedicationErrorBuilder.fromBindingErrors(bindingResult));
        }

        MedicationDto savedMedication = this.droneDispatchService.createMedication(medicationDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(savedMedication.getCode()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/medications")
    public ResponseEntity<Iterable<MedicationDto>> getAvailableMedications(){
        List<MedicationDto> medicationDtos = this.droneDispatchService.getAvailableMedications();
        ResponseEntity responseEntity = new ResponseEntity (medicationDtos, HttpStatus.ACCEPTED);

        return responseEntity;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MedicationValidationError handleException(Exception exception)
    {
        return new MedicationValidationError(exception.getMessage());
    }
}
