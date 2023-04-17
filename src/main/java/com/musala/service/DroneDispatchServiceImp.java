package com.musala.service;

import com.musala.domain.Drone;
import com.musala.domain.DroneState;
import com.musala.domain.Medication;
import com.musala.domain.dto.DroneDto;
import com.musala.domain.dto.MedicationDto;
import com.musala.repo.DroneRepository;
import com.musala.repo.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("droneDispatchServiceImpl")
public class DroneDispatchServiceImp implements DroneDispatchService{

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    public DroneDispatchServiceImp(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    public DroneDto registerDrone(DroneDto droneDto)
    {
        Drone newDrone = new Drone();
        newDrone.setDroneModel(droneDto.getDroneModel());
        newDrone.setDroneState(droneDto.getDroneState());
        newDrone.setBatteryCapacity(droneDto.getBatteryCapacity());
        newDrone.setSerialNo(droneDto.getSerialNo());
        newDrone.setWeightLimit(droneDto.getWeightLimit());

        if(droneExists(droneDto.getSerialNo()))
        {
            throw new RuntimeException("Drone Already Registered..");
        }

        Drone savedDrone = (Drone) droneRepository.save(newDrone);
        Optional.ofNullable(savedDrone).orElseThrow( ( ) -> new RuntimeException("Couldn't persist new Drone"));
        droneDto.setId(savedDrone.getId());

        return  droneDto;

    }

    public void setDroneMedications(List<MedicationDto> medications, String serialNo)
    {
        Optional<Drone> droneOptional = this.getDrone(serialNo);
        Drone drone = droneOptional.isPresent()? droneOptional.get() : droneOptional.orElseThrow(() -> new RuntimeException("Drone Specified Not Found"));

        List<Medication> medicationList = medications.stream().map((medicationDto) -> {
            Medication medication = new Medication();
            medication.setCode(medicationDto.getCode());
            medication.setName(medicationDto.getName());
            medication.setWeight(medicationDto.getWeight());
            return medication;
        }).collect(Collectors.toList());

        medicationList.stream().forEach((medication -> { drone.addMedication(medication);}));
        this.droneRepository.save(drone);

    }

    public List<MedicationDto> getLoadedDroneMedications(String serialNo)
    {

       if (!this.getDrone(serialNo).isPresent())
        {
            return new ArrayList<MedicationDto>();
        }

      List<MedicationDto> medicationDtos =  this.getDrone(serialNo).get().getMedications().stream().map((droneMedication)->{
            MedicationDto medicationDto = new MedicationDto();
            Medication medication = droneMedication.getMedication();
            medicationDto.setCode(medication.getCode());
            medicationDto.setName(medication.getName());
            medicationDto.setWeight(medication.getWeight());
            return medicationDto;
        }).collect(Collectors.toList());
      return medicationDtos;

    }

    public List<DroneDto> getDronesAvailableForLoading()
    {
       List<DroneDto> droneDtos = this.droneRepository.findByDroneState(DroneState.IDLE).stream().map((drone -> {
                                                            DroneDto droneDto = new DroneDto();
                                                            droneDto.setDroneModel(drone.getDroneModel());
                                                            droneDto.setDroneState(drone.getDroneState());
                                                            droneDto.setSerialNo(drone.getSerialNo());
                                                            droneDto.setWeightLimit(drone.getWeightLimit());
                                                            droneDto.setBatteryCapacity(drone.getBatteryCapacity());
                                                            droneDto.setId(drone.getId());
                                                            return  droneDto;
                                                            })).collect(Collectors.toList());

        return droneDtos;
    }

    public Double getDroneBatteryLevel(String serialNo){

       Optional<Drone> droneOptional = this.getDrone(serialNo);

       if(droneOptional.isPresent())
       {
          return droneOptional.get().getBatteryCapacity();
       }

        return 0D;

    }

    @Override
    public Optional<Drone> getDrone(String serialNo) {
        return this.droneRepository.findBySerialNo(serialNo);
    }

    public boolean droneExists(String serialNo){

        Optional<Drone> droneOptional = this.droneRepository.findBySerialNo(serialNo);

        return droneOptional.isPresent()? true : false;
    }
}
