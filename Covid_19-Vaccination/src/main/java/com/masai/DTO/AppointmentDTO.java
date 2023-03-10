package com.masai.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

	private long bookingId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOfBooking;
	
	private String centername;
	
	private String address;
}
