package com.musala.rest;

import com.musala.domain.dto.ApiResponse;
import com.musala.domain.dto.MedicationDto;
import com.musala.domain.dto.ResponseCode;
import com.musala.service.DroneDispatchService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medication")
public class MedicationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MedicationController.class);
    @Autowired()
    private DroneDispatchService droneDispatchService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createMedication(@Valid() @RequestBody MedicationDto medicationDto, BindingResult bindingResult){

        ApiResponse apiResponse = new ApiResponse();

        if( bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().stream().forEach(System.out::println);
            apiResponse.setCode(ResponseCode.FAILURE);
            apiResponse.setMessage("Could Not Create Medication,");
            return ResponseEntity.badRequest().body(apiResponse);
//            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        MedicationDto savedMedication = this.droneDispatchService.createMedication(medicationDto);
        apiResponse.setMessage(savedMedication.getName()+" Successfully Created.");

        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity(apiResponse, HttpStatus.CREATED);

        return responseEntity;
    }

    @GetMapping("/medications")
    public ResponseEntity<List<MedicationDto>> getAvailableMedications(){
        List<MedicationDto> medicationDtos = this.droneDispatchService.getAvailableMedications();
        ResponseEntity responseEntity = new ResponseEntity (medicationDtos, HttpStatus.ACCEPTED);

        return responseEntity;
    }
}
