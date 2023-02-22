package com.masai.service;

import com.masai.exception.AppointmentException;
import com.masai.model.Appointment;

public interface AppointmentService {

	public Appointment getAppointmentByBookingId(Long bookingId)throws AppointmentException ;
	
	public Appointment addNewAppointment(Appointment appointment) throws AppointmentException;
	
	public Appointment updateAppointment(Appointment appointment) throws AppointmentException;
	
	public boolean DeleteAppointment(Appointment appointment) throws AppointmentException;
	
	
	
}
