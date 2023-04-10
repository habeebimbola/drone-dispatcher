package com.musala.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.musala.domain.Drone;
import com.musala.domain.DroneModel;

@JsonSerialize
public class DroneDto extends Drone {

    private String serialNo;

    private DroneModel droneModel;

    private Double weightLimit;

    private Double batteryCapacity;






}
