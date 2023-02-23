package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Login;

public interface RegistrationRepository extends JpaRepository<Login, Integer> {

	public Optional<Login> findByMobileNo(String mobileNo);
}
