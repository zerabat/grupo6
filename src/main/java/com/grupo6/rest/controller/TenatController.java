package com.grupo6.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo6.service.TenantService;

@RestController
public class TenatController {
	
	@Autowired
	private TenantService tenatService;
	

@RequestMapping(path = "/crearNuevoTenat", method = RequestMethod.PUT )
public ResponseEntity<?> createTicket(@RequestParam (name ="nombreTenant", required = true) String nombreTenat) {
	
//	TenantContext.setCurrentTenant(tenantName);
	tenatService.createTenat(nombreTenat);
	return new ResponseEntity<Object>( HttpStatus.OK);
}


}


