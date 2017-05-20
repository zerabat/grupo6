package com.grupo6.rest.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo6.config.TenantContext;
import com.grupo6.service.TicketTypeService;

@RestController
public class TicketTypeController {

	@Autowired
	private TicketTypeService ticketTypeService;

	@RequestMapping(path = "/createTicketType", method = RequestMethod.PUT )
	public ResponseEntity<Long> createTicket(@RequestHeader("X-TenantID") String tenantName,
											@RequestParam (name ="nombreEvento", required = true) String nombreEvento,
										    @RequestParam (name ="fechaEvento", required = true) Date fechaEvento) {
		
		
		TenantContext.setCurrentTenant(tenantName);
		Long ticketId = ticketTypeService.createTicket(nombreEvento,fechaEvento);
		
		return new ResponseEntity<Long>(ticketId, HttpStatus.OK);
	}
	
//	
//	@RequestMapping(path = "/api/tenantService/{tenantID}", method = RequestMethod.GET)
//	public ResponseEntity<List<TenantServiceDTO>> findAllByTenantId(@PathVariable(name = "tenantID", required = true) long tenantID){
//		return new ResponseEntity<List<TenantServiceDTO>>(mapper.mapAsList(service.findByTenantId(tenantID), TenantServiceDTO.class), HttpStatus.OK);
//	}
}
