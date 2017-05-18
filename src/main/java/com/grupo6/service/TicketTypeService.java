package com.grupo6.service;

import java.util.Date;

import com.grupo6.rest.dto.TicketTypeDTO;

public interface TicketTypeService {
	
	Long createTicket(String nombreEvento, Date fechaEvento);
	
	Long createTicket(TicketTypeDTO tDTO);
	
}
