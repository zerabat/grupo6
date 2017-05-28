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
import com.grupo6.persistence.model.Usuario;
import com.grupo6.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	// @RequestMapping(path = "/crearNuevoTenat", method = RequestMethod.PUT)
	// public ResponseEntity<?> createTicket(@RequestParam(name =
	// "nombreTenant", required = true) String nombreTenat) {
	//
	// // TenantContext.setCurrentTenant(tenantName);
	// tenatService.createTenat(nombreTenat);
	// return new ResponseEntity<Object>(HttpStatus.OK);
	// }

	@RequestMapping(path = "/altaUsuarioFinal/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaAdministradorTenant(@RequestHeader("X-TenantID") String tenantName,
			@RequestBody Usuario dtos) {

		TenantContext.setCurrentTenant(tenantName);
		userService.altaUsuario(dtos);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/loginUsuarioFinal/", method = RequestMethod.POST)
	public ResponseEntity<?> loginUsuarioFinal(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password) {

		TenantContext.setCurrentTenant(tenantName);

		Optional<Usuario> usuario = userService.loginEmailPassword(email, password);
		if (usuario.isPresent()) {
			if (usuario.get().getPassword().equals(password)) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", usuario);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(path = "/loginUsuarioFinalGmail/", method = RequestMethod.POST)
	public ResponseEntity<?> loginUsuarioFinalGmail(@RequestHeader("X-TenantID") String tenantName,
			@RequestBody String data, HttpServletRequest request){

		TenantContext.setCurrentTenant(tenantName);
		
//		JSONObject json = new JSONObject(data);
//		String email = json.getString("email");
//		String id = json.getString("id");
		
//		Optional<Usuario> usuario = userService.loginEmailPassword(email, id);
//		if (usuario.isPresent()) {
//			if (usuario.get().getPassword().equals(id)) {
//				HttpSession sesion = request.getSession();
//				sesion.setAttribute("usuario", usuario);
//				return new ResponseEntity<Object>(HttpStatus.OK);
//			}
//		} else {
//			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
//		}
		return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(path = "/pruebalogin/", method = RequestMethod.PUT)
	public ResponseEntity<?> buscarEvento(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "busqueda", required = true) String busqueda) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
		if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
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
