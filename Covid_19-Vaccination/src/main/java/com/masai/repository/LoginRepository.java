package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.LoginUserSession;

public interface LoginRepository extends JpaRepository<LoginUserSession, Integer> {

	public Optional<LoginUserSession> findByUserKey(String userKey);
}
