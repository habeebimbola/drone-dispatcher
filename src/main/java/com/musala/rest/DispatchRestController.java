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
public class DispatchRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchRestController.class);

    private final DroneDispatchService droneDispatchService;

    public DispatchRestController(@Qualifier("droneDispatchServiceImpl") DroneDispatchService droneDispatchService) {
        this.droneDispatchService = droneDispatchService;
    }

    @GetMapping("/drone/{droneId}")
    public ResponseEntity< DroneDto> getDrone(@PathVariable("droneId") Integer droneId)
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

    @PostMapping("/loadDrone/{droneId}")
    public ResponseEntity<DroneDto> loadDroneMedications( @PathVariable("droneId") Integer droneId ,  @RequestBody() List<MedicationDto> medicationDtos)
    {
        ResponseEntity<DroneDto> dtoResponseEntity = new ResponseEntity(HttpStatus.ACCEPTED);
        return dtoResponseEntity;
    }

    @GetMapping("/loadedMedications/{droneId}")
    public ResponseEntity<List<DroneDto>> getLoadedMedications(@PathVariable("droneId") Integer droneId){
        ResponseEntity<List<DroneDto>> responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/availableDrones")
    public ResponseEntity<List<DroneDto>> availableDronesForLoading(){
        ResponseEntity<List<DroneDto>> availableDrones = new ResponseEntity(HttpStatus.OK);
        return availableDrones;
    }

    @GetMapping("/batteryLevel/{droneId}")
    public ResponseEntity<DroneDto> getBatteryLevel(@PathVariable("droneId") String droneId)
    {
        ResponseEntity<DroneDto> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }

}
