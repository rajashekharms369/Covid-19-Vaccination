package com.masai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.VaccineInventory;

public interface VaccineInventoryRepository extends JpaRepository<VaccineInventory, Integer>{
	
	public List<VaccineInventory> findByDate(LocalDate date);
	
	
}
