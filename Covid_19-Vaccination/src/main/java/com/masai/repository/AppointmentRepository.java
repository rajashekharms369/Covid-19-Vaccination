package com.masai.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query(value = "from Appointment a where a.bookingId = :id")
	public Optional<Appointment> getAppointmentByBookingId(@Param("id") Long bookingId);
	
	@Query(value ="from Appointment  where dateOfBooking = :date")
	public List<Appointment> getAllAppointmentsInDate(@Param("date") LocalDate dateofbooking);
}
