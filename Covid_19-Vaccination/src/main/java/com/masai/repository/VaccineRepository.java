package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Integer>{

}
