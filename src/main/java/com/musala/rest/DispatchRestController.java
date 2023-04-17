package com.musala.rest;

import com.musala.domain.Drone;
import com.musala.domain.dto.DroneDto;
import com.musala.domain.dto.MedicationDto;
import com.musala.service.DroneDispatchService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("drone-dispatch-service")
public class DispatchRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchRestController.class);

    private final DroneDispatchService droneDispatchService;

    public DispatchRestController(@Qualifier("droneDispatchServiceImpl") DroneDispatchService droneDispatchService) {
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
    public ResponseEntity<DroneDto> registerDrone(@Valid() @RequestBody() DroneDto droneDto, BindingResult bindingResult)
    {
        LOGGER.info("Invoking /register Handler "+droneDto.toString());

        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DroneDto createdDrone = this.droneDispatchService.registerDrone(droneDto);
        ResponseEntity<DroneDto> responseEntity = new ResponseEntity<>(createdDrone, HttpStatus.CREATED);

        return responseEntity;
    }

    @PostMapping("/drone/load/{serialNo}")
    public ResponseEntity<?> loadDroneWithMedications(@PathVariable("serialNo") String serialNo , @RequestBody() List<MedicationDto> medicationDtos)
    {
        Optional<Drone> droneOptional = this.droneDispatchService.getDrone(serialNo);
        if (!droneOptional.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.droneDispatchService.setDroneMedications(medicationDtos, serialNo);

        ResponseEntity<?> dtoResponseEntity = new ResponseEntity(HttpStatus.ACCEPTED);
        return dtoResponseEntity;
    }

    @GetMapping("/drone/medications/{serialNo}")
    public ResponseEntity<List<MedicationDto>> getLoadedMedications(@PathVariable("serialNo") String serialNo){

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
    public ResponseEntity<Double> getBatteryLevel(@PathVariable("serialNo") String serialNo)
    {
        Double batteryLevel = this.droneDispatchService.getDroneBatteryLevel(serialNo);
        ResponseEntity<Double> responseEntity = new ResponseEntity(batteryLevel, HttpStatus.OK);
        return responseEntity;
    }

}
