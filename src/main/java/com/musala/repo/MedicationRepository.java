package com.musala.repo;

import com.musala.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    public Optional<Medication> findByCode(String code);
}
