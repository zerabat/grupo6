package com.grupo6.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.AdministradorTenant;
import com.grupo6.service.AdministradorService;

@RestController
public class AdministradorController {

	@Autowired
	private AdministradorService administradorService;

	@RequestMapping(path = "/altaAdministradorTenat/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaAdministradorTenat(
			@RequestHeader("X-TenantID") String tenantName, @RequestBody AdministradorTenant dtos) {
		
		TenantContext.setCurrentTenant(tenantName);
		administradorService.agregarAdministrador(dtos);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

		
//	@RequestMapping(path = "/agregarAdminATenat", method = RequestMethod.PUT)
//	public ResponseEntity<?> agregarAdministradorATenant(@RequestHeader("X-TenantID") String tenantName,
//			@RequestBody( AdministradorTenant adminTenant ) {
//		
//		TenantContext.setCurrentTenant(tenantName);
//		tenatService.agregarAdministrador(adminTenant);
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}

}
