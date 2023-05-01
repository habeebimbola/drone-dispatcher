package com.musala.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Drone")
@Table(name = "DRONE")
public class Drone implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SERIAL_NO", nullable = false)
    @Size(max = 100, message = "Serial Number Length Cannot Be More Than 100 Or Less Than 5 Characters", min = 5)
    private String serialNo;

    @Column(name = "MODEL", nullable = false )
    @Enumerated( value = EnumType.STRING)
    private DroneModel droneModel;

    @Column(name = "WEIGHT_LIMIT", nullable = false)
    private Double weightLimit;

    @Column(name = "BATTERY_CAPACITY", nullable = false)
    private Double batteryCapacity;

    @Column(name = "STATE")
    @Enumerated(value = EnumType.ORDINAL)
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

    public List<DroneMedication> getMedications() {
        return droneMedications;
    }

    public void setMedications(List<DroneMedication> medications) {
        this.droneMedications = medications;
    }

    public void addMedication(Medication medication){
        DroneMedication droneMedication = new DroneMedication(this, medication);
        droneMedications.add(droneMedication);
        medication.getDroneMedications().add(droneMedication);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drone)) return false;
        Drone drone = (Drone) o;
        return id.equals(drone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
