package com.musala.rest;

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
    public ResponseEntity<DroneDto> loadDroneMedications( @PathVariable("serialNo") String serialNo ,  @RequestBody() List<MedicationDto> medicationDtos)
    {
        ResponseEntity<DroneDto> dtoResponseEntity = new ResponseEntity(HttpStatus.ACCEPTED);
        return dtoResponseEntity;
    }

    @GetMapping("/drone/medications/{serialNo}")
    public ResponseEntity<List<DroneDto>> getLoadedMedications(@PathVariable("serialNo") String serialNo){
        ResponseEntity<List<DroneDto>> responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/drone/available")
    public ResponseEntity<List<DroneDto>> availableDronesForLoading(){
        List<DroneDto> availableForLoading = this.droneDispatchService.getDronesAvailableForLoading();
        ResponseEntity<List<DroneDto>> availableDrones = new ResponseEntity(availableForLoading, HttpStatus.OK);
        return availableDrones;
    }

    @GetMapping("/{serialNo}/battery-level")
    public ResponseEntity<Double> getBatteryLevel(@PathVariable("serialNo") String serialNo)
    {
        Double batteryLevel = this.droneDispatchService.getDroneBatteryLevel(serialNo);
        ResponseEntity<Double> responseEntity = new ResponseEntity(batteryLevel, HttpStatus.OK);
        return responseEntity;
    }

}
