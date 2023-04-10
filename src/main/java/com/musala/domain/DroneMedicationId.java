package com.musala.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DroneMedicationId {

    @Column(name = "DRONE_ID")
    private Integer droneId;
    @Column(name = "MEDICATION_ID")
    private Integer medicationId;

    public Integer getDroneId() {
        return droneId;
    }

    public void setDroneId(Integer droneId) {
        this.droneId = droneId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }
}
