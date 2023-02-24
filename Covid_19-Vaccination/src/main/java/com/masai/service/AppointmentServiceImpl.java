package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AppointmentException;
import com.masai.model.Appointment;
import com.masai.model.VaccinationCenter;
import com.masai.repository.AppointmentRepository;
import com.masai.repository.VaccinationCenterRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private VaccinationCenterRepository vaccinationCenterRepository;

	@Override
	public Appointment getAppointmentByBookingId(Long bookingId) throws AppointmentException {
		Optional<Appointment> optional = appointmentRepository.getAppointmentByBookingId(bookingId);
		
		return optional.orElseThrow(() -> new AppointmentException("No Appointment available! Please try with another booking..."));
		
	}

	@Override
	public Appointment addNewAppointment(Appointment appointment,Integer centerId) throws AppointmentException {
		
		Optional<VaccinationCenter> centerOpt = vaccinationCenterRepository.findById(centerId);
		
		if(centerOpt.isEmpty()) {
			throw new AppointmentException("No center found with this id!");
		}
		
		
		
		Optional<Appointment> optional = appointmentRepository.getAppointmentByBookingId(appointment.getBookingId());
		
		List<Appointment> appointmentList = appointmentRepository.getAllAppointmentsInDate(appointment.getDateOfBooking());
		int count=0;
		for(Appointment app:appointmentList) {
			if(app.getSlot()!=null) {
				count++;
			}
		}
		if(count==9) {
			throw new AppointmentException("All slots are booked for date "+appointment.getDateOfBooking()+" choose different day...");
		}
		
		
		for(Appointment app:appointmentList) {
			if(app.getSlot()==appointment.getSlot()) {
				throw new AppointmentException("Slot Unavailabe please select other slot...");
			}
		}
		
		if(optional.isEmpty()) {
			appointment.setVaccinationCenter(centerOpt.get());
			centerOpt.get().getAppointments().add(appointment);
		
		Appointment saveAppointment = appointmentRepository.save(appointment);
		
		return saveAppointment;
		}else {
			throw new AppointmentException("Appointment already exist please make new one...");
		}
	}


	@Override
	public Appointment updateAppointment( Appointment appointment) throws AppointmentException {
		Optional<Appointment> optional = appointmentRepository.getAppointmentByBookingId(appointment.getBookingId());
		List<Appointment> appointmentList = appointmentRepository.getAllAppointmentsInDate(appointment.getDateOfBooking());
		int count=0;
		for(Appointment app:appointmentList) {
			if(app.getSlot()!=null) {
				count++;
			}
		}
		if(count==9) {
			throw new AppointmentException("All slots are booked for date "+appointment.getDateOfBooking()+" choose different day...");
		}
		
		
		for(Appointment app:appointmentList) {
			if(app.getSlot()==appointment.getSlot()) {
				throw new AppointmentException("Slot Unavailabe please select other slot...");
			}
		}
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

	@Override
	public List<Appointment> getAllAppointmentsOnPerticularDate(LocalDate dateofbooking) throws AppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

}
