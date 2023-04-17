package com.musala.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.musala.domain.Drone;
import com.musala.domain.DroneModel;
import com.musala.domain.DroneState;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@JsonSerialize
public class DroneDto extends Drone {

    @JsonIgnore
    private Integer id;

    @Size(max = 100, message = "Invalid Serial Number Length. Must Be Less Than 100", min = 10)
    @JsonProperty("serialNo")
    private String serialNo;

    @JsonProperty("droneModel")
    private DroneModel droneModel;

    @Max(value = 500, message = "Weight Limit Exceeded. 500gr Maximum")
    @JsonProperty("weightLimit")
    private Double weightLimit;

    @Positive( message = "Invalid Minimum Drone Weight Capacity ")
    @JsonProperty("batteryCapacity")
    private Double batteryCapacity;

    @JsonProperty("droneState")
    private DroneState droneState;


    @Override
    public String toString() {
        return "DroneDto{" +
                "serialNo='" +getSerialNo()  + '\'' +
                ", droneModel=" + getDroneModel() +
                ", weightLimit=" + getWeightLimit() +
                ", batteryCapacity=" + getBatteryCapacity() +
                ", droneState=" + getDroneState() +
                '}';
    }
}
