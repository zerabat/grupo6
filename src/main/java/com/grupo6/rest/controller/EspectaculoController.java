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
import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.Usuario;
import com.grupo6.rest.dto.EspectaculoDTO;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;
import com.grupo6.service.EspectaculoService;
import com.grupo6.service.RealizacionEspectaculoService;

@RestController
public class EspectaculoController {

//	@Autowired
//	private AdministradorService administradorService;
	
	@Autowired
	private EspectaculoService espectaculoService;
	
	@Autowired
	private RealizacionEspectaculoService realizacionEspectaculoService;

	
	@RequestMapping(path = "/altaEspectaculo/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaEspectaculo(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email, @RequestBody Espectaculo espectaculo) {
		
		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
		}
		TenantContext.setCurrentTenant(tenantName);
		espectaculoService.agregarEspectaculo(espectaculo);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/modificarEspectaculo/", method = RequestMethod.PUT)
	public ResponseEntity<?> modificarEspectaculo(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email, @RequestBody Espectaculo espectaculo) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenat");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
		}
		TenantContext.setCurrentTenant(tenantName);
		espectaculoService.modificarEspectaculo(espectaculo);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}


	@RequestMapping(path = "/obtenerEspectaculosAdmin/", method = RequestMethod.GET)
	public ResponseEntity<List<EspectaculoDTO>> obtenerEspectaculosAdmin(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession().getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<List<EspectaculoDTO>>(HttpStatus.NOT_ACCEPTABLE);
		}

		List <EspectaculoDTO> espectaculos = espectaculoService.obtenerEspectaculos();
		return new ResponseEntity<List <EspectaculoDTO>>(espectaculos,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/crearRelizacionEspectaculo/", method = RequestMethod.PUT)
	public ResponseEntity<List<EspectaculoDTO>> crearRealizacionEspectaculo(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email, @RequestBody RealizacionEspectaculoDTO realizacionEspectaculoDTO ) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> u = (Optional<AdministradorTenant>) request.getSession().getAttribute("administradorTenant");
		if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
			return new ResponseEntity<List<EspectaculoDTO>>(HttpStatus.NOT_ACCEPTABLE);
		}
		realizacionEspectaculoService.altaRealizacionEspectaculo(realizacionEspectaculoDTO);
		List <EspectaculoDTO> espectaculos = espectaculoService.obtenerEspectaculos();
		return new ResponseEntity<List <EspectaculoDTO>>(espectaculos,HttpStatus.OK);
	}
	


	@RequestMapping(path = "/obtenerEspectaculosUsuario/", method = RequestMethod.GET)
	public ResponseEntity<List<EspectaculoDTO>> obtenerEspectaculosUsr(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<Usuario> a = (Optional<Usuario>) request.getSession().getAttribute("usuario");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<List<EspectaculoDTO>>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		
		List <EspectaculoDTO> espectaculos = espectaculoService.obtenerEspectaculos();
		
		return new ResponseEntity<List <EspectaculoDTO>>(espectaculos,HttpStatus.OK);
	}

}
