package com.musala.repo;

import com.musala.domain.Drone;
import com.musala.domain.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Integer> {

    public abstract List<Drone> findByDroneState(DroneState droneState);

    public abstract Optional<Drone> findBySerialNo(String serialNo);
}
