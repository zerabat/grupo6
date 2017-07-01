package com.grupo6.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.Portero;
import com.grupo6.rest.dto.EspectaculoFullDTO;
import com.grupo6.service.EspectaculoService;
import com.grupo6.service.PorteroService;
import com.grupo6.service.RealizacionEspectaculoService;
import com.grupo6.util.page.PageUtils;

@RestController
public class PorteroController {
	
	@Autowired
	PorteroService  porteroService; 
	
	@Autowired
	RealizacionEspectaculoService realizacionEspectaculoService;

	@Autowired
	EspectaculoService espectaculoService;
	
	@RequestMapping(path = "/loginPortero/", method = RequestMethod.PUT)
	public ResponseEntity<?> loginAdministradorTenant(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "cedula", required = true) String cedula,
			@RequestParam(name = "password", required = true) String password) {

		TenantContext.setCurrentTenant(tenantName);

		Optional<Portero> portero = porteroService.login(cedula);
		if (portero.isPresent()) {
			String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
			if (portero.get().getPassword().equals(sha256hex)) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("portero", portero);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(path = "/verEspectaculosYSusRealizacionesDeHoy/", method = RequestMethod.GET)
	public ResponseEntity<List<EspectaculoFullDTO>> verProximosEspectaculosYRealizaciones(
			@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "cedula", required = true) String cedula) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<Portero> p = (Optional<Portero>) request.getSession().getAttribute("portero");
		if (p == null || !p.isPresent() || !p.get().getCedula().equals(cedula)) {
			return new ResponseEntity<List<EspectaculoFullDTO>>(HttpStatus.FORBIDDEN);
		}
		else{
			List<Espectaculo> l = this.espectaculoService.findAllHoy();
			List<EspectaculoFullDTO> ret = this.mapearEspectaculos(l);
			return new ResponseEntity<List<EspectaculoFullDTO>>(ret, HttpStatus.OK);
		}
			
	}
	
	private List<EspectaculoFullDTO> mapearEspectaculos(List<Espectaculo> l) {
		return l.stream().map(x -> new EspectaculoFullDTO(x)).collect(Collectors.toList());
	}

	
	@RequestMapping(path = "/verificarCodigoQR/", method = RequestMethod.GET)
	public ResponseEntity<?> verificarQR(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "cedula", required = true) String cedula,
			@RequestParam(name = "qr", required = true) String qr) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<Portero> p = (Optional<Portero>) request.getSession().getAttribute("portero");
		if (p == null || !p.isPresent() || !p.get().getCedula().equals(cedula)) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
//		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(qr);
		
		Boolean suL = realizacionEspectaculoService.verificarQR(qr, cedula);
		return new ResponseEntity<Object>(suL, HttpStatus.OK);
	}
	
}
