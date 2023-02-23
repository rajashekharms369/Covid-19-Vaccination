package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.VaccinationCenter;

public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Integer>{

}