package com.grupo6.service.bean;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.TicketType;
import com.grupo6.persistence.repository.TicketTypeRepository;
import com.grupo6.rest.dto.TicketTypeDTO;
import com.grupo6.service.TicketTypeService;

@Service
public class TicketTypeServiceBean implements TicketTypeService{
	
	@Autowired 
	TicketTypeRepository ticketRepo;

	@Override
	public Long createTicket(TicketTypeDTO ttDTO) {
		
		TicketType tt = new TicketType(ttDTO);
		ticketRepo.save(tt);
		return tt.getId();
	}

	@Override
	public Long createTicket(String nombreEvento, Date fechaEvento) {
		TicketType tt = new TicketType(nombreEvento,fechaEvento);
		ticketRepo.save(tt);
		return tt.getId();
	}
}
