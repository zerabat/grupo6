package com.grupo6.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.AdministradorTenant;
import com.grupo6.service.AdministradorService;
import com.grupo6.service.SuperAdministradorService;
import com.grupo6.service.TenantService;

@RestController
public class SuperAdminController {


	@Autowired
	private SuperAdministradorService superAdministradorService;
	
	@Autowired
	private TenantService tenatService;
	
	@Autowired
	private AdministradorService administradorService;

	@RequestMapping(path = "/crearNuevoTenat", method = RequestMethod.PUT)
	public ResponseEntity<?> creatTenant(@RequestParam(name = "nombreTenant", required = true) String nombreTenat,
			@RequestParam(name = "password", required = true) String password) {

		// TenantContext.setCurrentTenant(tenantName);
		if (superAdministradorService.esSuperAdmin(password)){
			tenatService.createTenat(nombreTenat);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@RequestMapping(path = "/altaAdministradorTenat/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaAdministradorTenat(@RequestHeader("X-TenantID") String tenantName,
			@RequestParam(name = "password", required = true) String password,
			@RequestBody AdministradorTenant dtos) {
		
		if (superAdministradorService.esSuperAdmin(password)){
			TenantContext.setCurrentTenant(tenantName);
			administradorService.agregarAdministrador(dtos);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
		
	}
}
