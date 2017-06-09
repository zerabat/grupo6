package com.grupo6.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import com.grupo6.persistence.model.Sala;
import com.grupo6.service.SalaService;

@RestController
public class SalaController {

	@Autowired
	private SalaService salaService;

	@RequestMapping(path = "/altaNuevaSala/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaNuevaSala(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email, @RequestBody Sala sala) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession().getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}

		salaService.altaSala(sala);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/modificarSala/", method = RequestMethod.POST)
	public ResponseEntity<?> modificarSala(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email, @RequestBody Sala sala) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession().getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}

		salaService.altaSala(sala);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/obtenerSalas/", method = RequestMethod.GET)
	public ResponseEntity<List<Sala>> modificarSala(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession().getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<List<Sala>>(HttpStatus.FORBIDDEN);
		}

		List <Sala> salas = salaService.obtenerSalas();
		return new ResponseEntity<List <Sala>>(salas,HttpStatus.OK);
	}

}
