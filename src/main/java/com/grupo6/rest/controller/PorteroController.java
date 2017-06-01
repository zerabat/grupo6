package com.grupo6.rest.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class PorteroController {

//	@Autowired
//	private AdministradorService administradorService;

//	@RequestMapping(path = "/altaAdministradorTenat/", method = RequestMethod.PUT)
//	public ResponseEntity<?> altaAdministradorTenat(
//			@RequestHeader("X-TenantID") String tenantName, @RequestBody AdministradorTenant dtos) {
//		
//		TenantContext.setCurrentTenant(tenantName);
//		administradorService.agregarAdministrador(dtos);
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}
//
//	@RequestMapping(path = "/altaVendedor/", method = RequestMethod.PUT)
//	public ResponseEntity<?> altaVendedor(
//			@RequestHeader("X-TenantID") String tenantName, @RequestBody Vendedor vendedor) {
//		
//		TenantContext.setCurrentTenant(tenantName);
//		administradorService.agregarVendedor(vendedor);
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}
//		
//	@RequestMapping(path = "/altaPortero/", method = RequestMethod.PUT)
//	public ResponseEntity<?> altaPortero(
//			@RequestHeader("X-TenantID") String tenantName, @RequestBody Portero portero) {
//		
//		TenantContext.setCurrentTenant(tenantName);
//		administradorService.agregarPortero(portero);
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}
	
//	@RequestMapping(path = "/agregarAdminATenat", method = RequestMethod.PUT)
//	public ResponseEntity<?> agregarAdministradorATenant(@RequestHeader("X-TenantID") String tenantName,
//			@RequestBody( AdministradorTenant adminTenant ) {
//		
//		TenantContext.setCurrentTenant(tenantName);
//		tenatService.agregarAdministrador(adminTenant);
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}

}
