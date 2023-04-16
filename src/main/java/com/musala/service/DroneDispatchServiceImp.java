package com.musala.service;

import com.musala.domain.Drone;
import com.musala.domain.DroneState;
import com.musala.domain.dto.DroneDto;
import com.musala.domain.dto.MedicationDto;
import com.musala.repo.DroneRepository;
import com.musala.repo.MedicationRepository;
import org.springframework.stereotype.Service;

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
        newDrone.setDroneState(DroneState.IDLE);
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

//        if (savedDrone savedDrone.getId() == null)
//            throw new RuntimeException("Couldn't persist new Drone");

        return  droneDto;

    }

    public void setDroneMedications(List<MedicationDto> medications, Integer droneId)
    {
//        this.droneRepository.findById(droneId).orElseThrow(()-> new RuntimeException("Supplied Drone ID doesn't exist")).setMedications(medications);
    }

    public List<MedicationDto> getLoadedDroneMedications(Integer droneId)
    {
//        this.droneRepository.findById(droneId).orElseGet(()-> new DroneDto()).getMedications().stream().map((medication -> { medication.getCode() }));
        return null;
    }

    public List<DroneDto> getDronesAvailableForLoading()
    {
       List<DroneDto> droneDtos = this.droneRepository.findByDroneState(DroneState.IDLE).stream().map((drone -> {
                                                            DroneDto droneDto = new DroneDto();
                                                            droneDto.setDroneModel(drone.getDroneModel());
                                                            droneDto.setDroneState(drone.getDroneState());
                                                            droneDto.setSerialNo(drone.getSerialNo());
                                                            return  droneDto;
                                                            })).collect(Collectors.toList());

        return droneDtos;
    }

    public Double getDroneBatteryLevel(String serialNo){

       Optional<Drone> droneOptional = this.droneRepository.findBySerialNo(serialNo);

       if(droneOptional.isPresent())
       {
          return droneOptional.get().getBatteryCapacity();
       }

        return 0D;

    }

    public boolean droneExists(String serialNo){

        Optional<Drone> droneOptional = this.droneRepository.findBySerialNo(serialNo);

        return droneOptional.isPresent()? true : false;
    }
}
