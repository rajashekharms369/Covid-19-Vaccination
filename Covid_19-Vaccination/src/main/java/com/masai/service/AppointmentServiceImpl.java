package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AppointmentException;
import com.masai.model.Appointment;
import com.masai.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public Appointment getAppointmentByBookingId(Long bookingId) throws AppointmentException {
		Optional<Appointment> optional = appointmentRepository.getAppointmentByBookingId(bookingId);
		
		return optional.orElseThrow(() -> new AppointmentException("No Appointment available! Please try with another booking..."));
		
	}

	@Override
	public Appointment addNewAppointment(Appointment appointment) throws AppointmentException {
		Optional<Appointment> optional = appointmentRepository.getAppointmentByBookingId(appointment.getBookingId());
		
		if(optional.isEmpty()) {
		
		Appointment saveAppointment = appointmentRepository.save(appointment);
		
		return saveAppointment;
		}else {
			throw new AppointmentException("Appointment already exist please make new one...");
		}
	}


	@Override
	public Appointment updateAppointment( Appointment appointment) throws AppointmentException {
		Optional<Appointment> optional = appointmentRepository.getAppointmentByBookingId(appointment.getBookingId());
		if(optional.isEmpty()) {
			throw new AppointmentException("No appointment found with this bookingId! please give right information...");
		}else {
			appointment.setBookingId(optional.get().getBookingId());
			return appointmentRepository.save(appointment);
		}
		
	}



	@Override
	public boolean DeleteAppointment(Appointment appointment) throws AppointmentException {
		Optional<Appointment> optional = appointmentRepository.getAppointmentByBookingId( appointment.getBookingId());
		if(optional.isEmpty()) {
			throw new AppointmentException("No appointment found with this bookingId! please give right information...");
		}else {
			appointmentRepository.delete(appointment);
			return true;
		}
	}

}
