package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.IdCard;

public interface IdCardRepository extends JpaRepository<IdCard, Integer> {

}
