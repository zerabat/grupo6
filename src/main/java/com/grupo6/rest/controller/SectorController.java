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
import com.grupo6.persistence.model.Sector;
import com.grupo6.service.SectorService;

@RestController
public class SectorController {

	@Autowired
	private SectorService sectorService;
	
	@RequestMapping(path = "/altaSector/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaNuevaSala(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email, @RequestBody Sector sector) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession().getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
		}
		sectorService.altaSector(sector);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/obtenerSectoresDeSala/", method = RequestMethod.GET)
	public ResponseEntity<List<Sector>> obtenerSectoresDeSala(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "idSala", required = true) String idSala) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession().getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<List<Sector>>(HttpStatus.NOT_ACCEPTABLE);
		}
		List <Sector> sectores = sectorService.obtenerSectoresSala(idSala);
		return new ResponseEntity<List <Sector>>(sectores,HttpStatus.OK);
	}

}
