package com.grupo6.rest.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.grupo6.persistence.model.Portero;
import com.grupo6.persistence.model.Vendedor;
import com.grupo6.service.AdministradorService;

@RestController
public class AdministradorController {

	@Autowired
	private AdministradorService administradorService;

	@RequestMapping(path = "/altaAdministradorTenat/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaAdministradorTenat(@RequestHeader("X-TenantID") String tenantName,
			@RequestBody AdministradorTenant dtos) {

		// TODO crear superadministrador que crea todos los tenant

		TenantContext.setCurrentTenant(tenantName);
		administradorService.agregarAdministrador(dtos);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/loginAdministradorTenant/", method = RequestMethod.PUT)
	public ResponseEntity<?> loginAdministradorTenant(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password) {

		TenantContext.setCurrentTenant(tenantName);

		Optional<AdministradorTenant> adminT = administradorService.login(email);
		if (adminT.isPresent()) {
			if (adminT.get().getPassowd().equals(password)) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("administradorTenant", adminT);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(path = "/altaVendedor/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaVendedor(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email, @RequestBody Vendedor vendedor) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenat");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
		}
		TenantContext.setCurrentTenant(tenantName);
		administradorService.agregarVendedor(vendedor);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/altaPortero/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaPortero(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email, @RequestBody Portero portero) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenat");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
		}
		administradorService.agregarPortero(portero);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	// @RequestMapping(path = "/agregarAdminATenat", method = RequestMethod.PUT)
	// public ResponseEntity<?>
	// agregarAdministradorATenant(@RequestHeader("X-TenantID") String
	// tenantName,
	// @RequestBody( AdministradorTenant adminTenant ) {
	//
	// TenantContext.setCurrentTenant(tenantName);
	// tenatService.agregarAdministrador(adminTenant);
	// return new ResponseEntity<Object>(HttpStatus.OK);
	// }

}
