package com.masai.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Appointment;
import com.masai.service.AppointmentService;


@RestController
public class AppointmentController {
//====== here we connect controller to been by autowiring appointment service interface ==========
	@Autowired
	private AppointmentService appointmentService;
	
	
	
//	=========== get mapping logic ======= 
	@GetMapping(value = "/appointment/{id}")
	public ResponseEntity<Appointment> getAppointmentBybookingIdHandller(@PathVariable("id") Long bookingId){
		
		Appointment appointment = appointmentService.getAppointmentByBookingId(bookingId);
		
		return new ResponseEntity<>(appointment, HttpStatus.OK);
		
	}
	
//========= saving new appointment =============
	@PostMapping(value = "/appointment/{centerId}")
	public ResponseEntity<Appointment> addNewAppointmentHandller(@RequestBody Appointment appointment, @PathVariable("centerId") Integer centerId){
		
		Appointment saveAppointment = appointmentService.addNewAppointment(appointment, centerId);
		
		return new ResponseEntity<>(saveAppointment, HttpStatus.CREATED);
		
	}
	
// =================	updating existing appointmenr ===========
	@PutMapping(value = "/appointment")
	public ResponseEntity<Appointment> updateAppointmentHandller(@RequestBody Appointment appointment){
		
		Appointment updateAppointment = appointmentService.updateAppointment(appointment);
		
		return new ResponseEntity<>(updateAppointment, HttpStatus.CREATED);
		
	}
	
//==============	deleting existing appointment =======
	
	@DeleteMapping(value = "/appointment")
	public ResponseEntity<Boolean> deleteAppointmentHandller(@RequestBody Appointment appointment){
		
		boolean deleteAppointment = appointmentService.DeleteAppointment(appointment);
		
		return new ResponseEntity<>(deleteAppointment, HttpStatus.OK);
		
	}
	
	
}
