package com.musala.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.musala.domain.DroneModel;
import com.musala.domain.DroneState;
import jakarta.validation.constraints.*;

@JsonSerialize
public class DroneDto  {

    @JsonIgnore
    private Integer id;

    @Size(max = 100, message = "Invalid Serial Number Length. Must Be At Least 10 Characters And Greater 100", min = 10)
    @JsonProperty("serialNo")
    private String serialNo;


    @NotNull(message = "Drone Model is Null. Please Fill In A Value")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public DroneModel getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(DroneModel droneModel) {
        this.droneModel = droneModel;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState;
    }
}
