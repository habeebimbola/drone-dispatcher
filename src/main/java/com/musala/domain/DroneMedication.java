package com.musala.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name="DroneMedication")
@Table(name = "DRONE_MEDICATION")
public class DroneMedication {

    @EmbeddedId
    private DroneMedicationId droneMedicationId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE", insertable = true)
    private LocalDateTime creationDate;

    @MapsId("droneId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DRONE_ID")
    private Drone drone;

    @MapsId("medicationId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEDICATION_ID")
    private Medication medication;

    public DroneMedication() {}

    public DroneMedication( Drone drone, Medication medication) {
        this.droneMedicationId = new DroneMedicationId(drone.getId(), medication.getId());
        this.drone = drone;
        this.medication = medication;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public DroneMedicationId getDroneMedicationId() {
        return droneMedicationId;
    }

    public void setDroneMedicationId(DroneMedicationId droneMedicationId) {
        this.droneMedicationId = droneMedicationId;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    @PrePersist
    void onCreation()
    {
        setCreationDate(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate(){
        setCreationDate(LocalDateTime.now());
    }


}
