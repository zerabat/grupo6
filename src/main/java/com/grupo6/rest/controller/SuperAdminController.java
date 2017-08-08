package com.grupo6.rest.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${tenantsMap}")
	private String stringTenantsMap;

	@Autowired
	private TenantContext tenantContext;

	@RequestMapping(path = "/crearNuevoTenat", method = RequestMethod.PUT)
	public ResponseEntity<?> creatTenant(@RequestParam(name = "nombreTenant", required = true) String nombreTenat,
			@RequestParam(name = "password", required = true) String password) throws IOException {

		if (superAdministradorService.esSuperAdmin(password)) {
			File file = Paths.get(stringTenantsMap).toFile();
			Properties tenantProperties = new Properties();
			tenantProperties.load(new FileInputStream(file));
			String tenantNames = tenantProperties.getProperty("tenantName");
			String databaseNames = tenantProperties.getProperty("databaseName");
			int i = tenantNames.split(",").length;
			tenantNames += "," + nombreTenat;
			databaseNames += "," + "tenant" + ++i;
			BufferedWriter bw = null;
			FileWriter fw = null;
			String content = new String();
			content += "tenantName:" + tenantNames + System.lineSeparator();
			content += "databaseName:" + databaseNames;
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			tenantContext.init();

			// tenatService.createTenat(nombreTenat);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(path = "/altaAdministradorTenat/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaAdministradorTenat(@RequestHeader("X-TenantID") String tenantName,
			@RequestParam(name = "password", required = true) String password, @RequestBody AdministradorTenant dtos) {

		if (superAdministradorService.esSuperAdmin(password)) {
			TenantContext.setCurrentTenant(tenantName);
			administradorService.agregarAdministrador(dtos);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(path = "/loginSuperAdmin/", method = RequestMethod.PUT)
	public ResponseEntity<?> loginSuperAdmin(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "password", required = true) String password) {

		if (superAdministradorService.esSuperAdmin(password)) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("superAdmin", "Super Administrador");
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			System.out.println("superAdmin NO encontrado");
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(path = "/cerrarSesionSuperAdmin/", method = RequestMethod.GET)
	public ResponseEntity<?> cerrarSesionSuperAdmin(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request) {

		System.out.println("cerrando sesion superAdmin");
		request.getSession().removeAttribute("superAdmin");
		request.getSession().invalidate();

		return new ResponseEntity<Object>(HttpStatus.OK);

	}
}
