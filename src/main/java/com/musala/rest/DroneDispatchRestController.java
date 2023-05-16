package com.musala.rest;

import com.musala.domain.Drone;
import com.musala.domain.dto.*;
import com.musala.service.DroneDispatchService;
import com.musala.validation.DroneValidationError;
import com.musala.validation.DroneValidationErrorBuilder;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("drone-dispatch-service")
public class DroneDispatchRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroneDispatchRestController.class);

    private final DroneDispatchService droneDispatchService;

    public DroneDispatchRestController(@Qualifier("droneDispatchServiceImpl") DroneDispatchService droneDispatchService) {
        this.droneDispatchService = droneDispatchService;
    }

    @GetMapping("/drone/{droneId}")
    public ResponseEntity<DroneDto> getDrone(@PathVariable("droneId") Integer droneId)
    {
        DroneDto droneDto = new DroneDto();
        ResponseEntity<DroneDto> responseEntity = new ResponseEntity<>( droneDto, HttpStatus.FOUND);
        return responseEntity;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDrone(@Valid() @RequestBody() DroneDto droneDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return ResponseEntity.badRequest().body(DroneValidationErrorBuilder.fromBindingErrors(bindingResult));
        }

        DroneDto createdDrone = this.droneDispatchService.registerDrone(droneDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{serialNo}"). buildAndExpand(createdDrone.getSerialNo()).toUri();

         ResponseEntity.created(location).build();;

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/drone/load/{serialNo}")
    public ResponseEntity<?> loadDroneWithMedications(@PathVariable("serialNo") String serialNo , @RequestBody() List<ApiRequest> medicationCodes)
    {
        Optional<Drone> droneOptional = this.droneDispatchService.getDrone(serialNo);
        ApiResponse apiResponse = new ApiResponse();

        if (!droneOptional.isPresent())
        {
            apiResponse.setMessage("Drone With Serial Number "+serialNo+" Not Found");
            apiResponse.setCode(ResponseCode.NOT_FOUND);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        this.droneDispatchService.setDroneMedications(medicationCodes, serialNo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(serialNo).toUri();

        return ResponseEntity.accepted().header("Location", location.toString()).build();

//        apiResponse.setMessage("Successfully Loaded Drone :"+serialNo+" With Medications.");
//        apiResponse.setCode(ResponseCode.SUCCESS);
//
//        ResponseEntity<ApiResponse> dtoResponseEntity = new ResponseEntity(apiResponse, HttpStatus.ACCEPTED);
//        return dtoResponseEntity;
    }

    @GetMapping("/drone/medications/{serialNo}")
    public ResponseEntity<List<MedicationDto>> getLoadedMedications(@PathVariable("serialNo") String serialNo){

        Optional<Drone> droneOptional = this.droneDispatchService.getDrone(serialNo);

        if(!droneOptional.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        List<MedicationDto> medicationDtoList = this.droneDispatchService.getLoadedDroneMedications(serialNo);

        ResponseEntity<List<MedicationDto>> responseEntity = new ResponseEntity( medicationDtoList,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/drone/available")
    public ResponseEntity<List<DroneDto>> availableDronesForLoading(){
        List<DroneDto> availableForLoading = this.droneDispatchService.getDronesAvailableForLoading();
        ResponseEntity<List<DroneDto>> availableDrones = new ResponseEntity(availableForLoading, HttpStatus.OK);
        return availableDrones;
    }

    @GetMapping("/drone/{serialNo}/battery-level")
    public ResponseEntity<DroneBatteryResponse> getDroneBatteryLevel(@PathVariable("serialNo") String serialNo)
    {
       Optional<Drone> droneOptional= this.droneDispatchService.getDrone(serialNo);

       if(!droneOptional.isPresent())
       {
           return ResponseEntity.notFound().build();
       }

        Double batteryLevel = this.droneDispatchService.getDroneBatteryLevel(serialNo);

        DroneBatteryResponse droneBatteryResponse = new DroneBatteryResponse();
        droneBatteryResponse.setBatteryLevel(batteryLevel);
        droneBatteryResponse.setSerialNo(serialNo);
        droneBatteryResponse.setCode(ResponseCode.SUCCESS);
        ResponseEntity<DroneBatteryResponse> responseEntity = new ResponseEntity(droneBatteryResponse, HttpStatus.OK);

        return responseEntity;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DroneValidationError handleException(Exception exception)
    {
        return new DroneValidationError(exception.getMessage());
    }

}
