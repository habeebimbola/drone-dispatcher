package com.musala.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Drone")
@Table(name = "DRONE")
public class Drone implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "SERIAL_NO", nullable = false)
    @Size(max = 100, message = "Serial Number Length Cannot Be More Than 100 Or Less Than 5 Characters", min = 5)
    private String serialNo;

    @Column(name = "MODEL", nullable = false )
    @Enumerated()
    private DroneModel droneModel;

    @Column(name = "WEIGHT_LIMIT", nullable = false)
    @Max(value = 500, message = "Weight Limit of 500 Exceeded. Please Adjust Weight")
    private Integer weightLimit;

    @Column(name = "BATTERY_CAPACITY", nullable = false)
    private Double batteryCapacity;

    @Column(name = "STATE")
    @Enumerated
    private DroneState droneState;

    @OneToMany(mappedBy = "drone" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DroneMedication> droneMedications = new ArrayList<>();

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

    public Integer getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Integer weightLimit) {
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

    public List<DroneMedication> getMedications() {
        return droneMedications;
    }

    public void setMedications(List<DroneMedication> medications) {
        this.droneMedications = medications;
    }
}
