package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Integer>{
	
  public Optional<Vaccine> findByVaccineName(String vaccineName);
}
