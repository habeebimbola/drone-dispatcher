package com.musala.service;

import com.musala.domain.Drone;
import com.musala.domain.DroneState;
import com.musala.domain.Medication;
import com.musala.domain.dto.ApiRequest;
import com.musala.domain.dto.DroneDto;
import com.musala.domain.dto.MedicationDto;
import com.musala.repo.DroneRepository;
import com.musala.repo.MedicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("droneDispatchServiceImpl")
public class DroneDispatchServiceImp implements DroneDispatchService{

    private final static Logger LOGGER = LoggerFactory.getLogger(DroneDispatchServiceImp.class);

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

        Drone savedDrone = droneRepository.save(newDrone);
        Optional.ofNullable(savedDrone).orElseThrow( ( ) -> new RuntimeException("Couldn't persist new Drone"));
        droneDto.setId(savedDrone.getId());

        return  droneDto;

    }

    public void setDroneMedications(List<ApiRequest> medications, String serialNo)
    {
        Optional<Drone> droneOptional = this.getDrone(serialNo);
        Drone drone = droneOptional.isPresent()? droneOptional.get() : droneOptional.orElseThrow(() -> new RuntimeException("Drone Specified Not Found"));

        List<Medication> medicationList = medications.stream().map((medicationDto) -> getMedicationByCode(medicationDto.getMedicationCode())

        ).collect(Collectors.toList());

        medicationList.stream().forEach((drone::addMedication));
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
            medicationDto.setImageURL(medication.getImageURL());
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

    @Override
    public MedicationDto createMedication(MedicationDto medicationDto) {
        Medication newMedication = new Medication();
        newMedication.setWeight(medicationDto.getWeight());
        newMedication.setName(medicationDto.getName());
        newMedication.setCode(medicationDto.getCode());
        newMedication.setImageURL(medicationDto.getImageURL());

        Medication savedMedication = this.medicationRepository.save(newMedication);
        Optional.ofNullable(savedMedication).orElseThrow(()->new RuntimeException("Could Not Create A New Medication"));

        return medicationDto;
    }

    @Override
    public List<MedicationDto> getAvailableMedications() {
        List<MedicationDto> medicationDtos =this.medicationRepository.findAll().stream().map((medication -> { MedicationDto medicationDto = new MedicationDto();
            medicationDto.setImageURL(medication.getImageURL());
            medicationDto.setWeight(medication.getWeight());
            medicationDto.setCode(medication.getCode());
            medicationDto.setName(medication.getName());
            return medicationDto;})).collect(Collectors.toList());

        return medicationDtos;
    }

    @Override
    public Medication getMedicationByCode(String code) {
        Optional<Medication> medicationOptional = this.medicationRepository.findByCode(code);

        if(medicationOptional.isPresent())
        {

            return medicationOptional.get();
        }
        return medicationOptional.orElse(new Medication());
    }

    public boolean droneExists(String serialNo){

        Optional<Drone> droneOptional = this.droneRepository.findBySerialNo(serialNo);

        return droneOptional.isPresent()? true : false;
    }

    @Scheduled(fixedRate = 10000)
    public void checkDroneBatterLevels()
    {
        LOGGER.info("Checking Drone Battery Level:");
    }
}
