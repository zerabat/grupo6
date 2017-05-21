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
import com.grupo6.persistence.model.Usuario;
import com.grupo6.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

//	@RequestMapping(path = "/crearNuevoTenat", method = RequestMethod.PUT)
//	public ResponseEntity<?> createTicket(@RequestParam(name = "nombreTenant", required = true) String nombreTenat) {
//
//		// TenantContext.setCurrentTenant(tenantName);
//		tenatService.createTenat(nombreTenat);
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}

	@RequestMapping(path = "/altaUsuarioFinal/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaAdministradorTenant(
			@RequestHeader("X-TenantID") String tenantName, @RequestBody Usuario dtos) {
		
		TenantContext.setCurrentTenant(tenantName);
		userService.altaUsuario(dtos);
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
