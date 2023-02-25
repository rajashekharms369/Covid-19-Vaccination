package com.masai.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.DTO.AppointmentDTO;
import com.masai.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query(value = "from Appointment a where a.bookingId = :id")
	public Optional<Appointment> getAppointmentByBookingId(@Param("id") Long bookingId);
	
	@Query(value ="from Appointment  where dateOfBooking = :date")
	public List<Appointment> getAllAppointmentsInDate(@Param("date") LocalDate dateofbooking);
	
    @Query(value = "select new com.masai.DTO.AppointmentDTO(a.bookingId, a.dateOfBooking, c.centername, c.address) from Appointment a INNER JOIN a.vaccinationCenter c  where a.dateOfBooking = :date AND a.bookingStatus = false")
	public List<AppointmentDTO> getAllAppointmentForMember(@Param("date") LocalDate dateofbooking);
}
