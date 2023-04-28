package com.musala.service;

import com.musala.domain.Drone;
import com.musala.domain.Medication;
import com.musala.domain.dto.ApiRequest;
import com.musala.domain.dto.DroneDto;
import com.musala.domain.dto.MedicationDto;

import java.util.List;
import java.util.Optional;

public interface DroneDispatchService {

    public abstract DroneDto registerDrone(DroneDto droneDto);

    public abstract void  setDroneMedications(List<ApiRequest> medications, String serialNo);

    public abstract List<MedicationDto> getLoadedDroneMedications(String serialNo);

    public abstract List<DroneDto> getDronesAvailableForLoading();

    public abstract Double getDroneBatteryLevel(String serialNo);


   public abstract Optional<Drone> getDrone(String serialNo);

    MedicationDto createMedication(MedicationDto medicationDto);

    List<MedicationDto> getAvailableMedications();

    public Medication getMedicationByCode(String code);
}
