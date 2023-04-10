package com.musala.service;

import com.musala.domain.dto.DroneDto;
import com.musala.domain.dto.MedicationDto;

import java.util.List;

public interface DroneDispatchService {

    public abstract DroneDto registerDrone(DroneDto droneDto);

    public abstract void  setDroneMedications(List<MedicationDto> medications, Integer droneId);

    public abstract List<MedicationDto> getLoadedDroneMedications(Integer droneId);

    public abstract List<DroneDto> getDronesAvailableForLoading();

    public abstract Double getDroneBatteryLevel(String serialNo);




}
