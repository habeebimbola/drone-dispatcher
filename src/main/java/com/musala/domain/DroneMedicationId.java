package com.musala.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DroneMedicationId)) return false;
        DroneMedicationId that = (DroneMedicationId) o;
        return droneId.equals(that.droneId) && medicationId.equals(that.medicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(droneId, medicationId);
    }
}
