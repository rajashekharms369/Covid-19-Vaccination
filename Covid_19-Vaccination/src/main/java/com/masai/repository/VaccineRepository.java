package com.masai.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.DTO.VaccineDTO;
import com.masai.model.Vaccine;
import com.masai.model.VaccineCount;

public interface VaccineRepository extends JpaRepository<Vaccine, Integer>{
  public Optional<Vaccine> findByVaccineName(String vaccineName);
  
  @Query(value = "select new com.masai.DTO.VaccineDTO(v.vaccineName,v.description) from Vaccine v ")
  public List<VaccineDTO> getAllVaccines();
  
  @Query(value = "select v.vaccinecount from Vaccine v where v.vaccineName = :name")
  public Optional<VaccineCount> getVaccineCountofPerticularVaccine(@Param("name") String vacciname);
  
  @Query(value = "from Vaccine where vaccineName = :na")
  public Optional<Vaccine> getVaccineByName(@Param("na") String name);
}
