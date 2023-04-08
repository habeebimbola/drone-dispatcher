package com.musala.rest;

import com.musala.domain.Drone;
import com.musala.domain.dto.DroneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispatchRestController {

    @GetMapping("/drone/{droneId}")
    public ResponseEntity< DroneDto> getDrone(@PathVariable("droneId") Integer droneId)
    {
        DroneDto droneDto = new DroneDto();
        ResponseEntity<DroneDto> responseEntity = new ResponseEntity<>( droneDto, HttpStatus.FOUND);
        return responseEntity;
    }
}
