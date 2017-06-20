package com.grupo6.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.Usuario;
import com.grupo6.rest.dto.EntradaDTO;
import com.grupo6.rest.dto.EspectaculoConTealizacionesDTO;
import com.grupo6.rest.dto.EspectaculoDTO;
import com.grupo6.rest.dto.EspectaculoFullDTO;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;
import com.grupo6.rest.dto.RealizacionEspectaculoDisponibilidadDTO;
import com.grupo6.service.EspectaculoService;
import com.grupo6.service.RealizacionEspectaculoService;
import com.grupo6.util.page.PageUtils;

@RestController
public class EspectaculoController {

	@Autowired
	private EspectaculoService espectaculoService;

	@Autowired
	private RealizacionEspectaculoService realizacionEspectaculoService;

	/* APIS DE ADMINISTRADOR DE UN TENANT */

	@RequestMapping(path = "/altaEspectaculo/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaEspectaculo(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email, @RequestBody EspectaculoDTO espectaculo) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
		espectaculoService.agregarEspectaculo(espectaculo);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/modificarEspectaculo/", method = RequestMethod.PUT)
	public ResponseEntity<?> modificarEspectaculo(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
			@RequestBody EspectaculoDTO espectaculo) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenat");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(email)) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
		TenantContext.setCurrentTenant(tenantName);
		espectaculoService.modificarEspectaculo(espectaculo);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/altaRelizacionEspectaculo/", method = RequestMethod.PUT)
	public ResponseEntity<?> altaRealizacionEspectaculo(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
			@RequestBody RealizacionEspectaculoDTO realizacionEspectaculoDTO) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> u = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenant");
		if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
			return new ResponseEntity<List<RealizacionEspectaculoDTO>>(HttpStatus.FORBIDDEN);
		}
		realizacionEspectaculoService.altaRealizacionEspectaculo(realizacionEspectaculoDTO);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(path = "/modificarRelizacionEspectaculo/", method = RequestMethod.PUT)
	public ResponseEntity<?> modificarRealizacionEspectaculo(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
			@RequestBody RealizacionEspectaculoDTO realizacionEspectaculoDTO) {

		// Si es necesaria se hace pero hay que tener ojo porque implica borrar
		// las entradas creadas

		// TenantContext.setCurrentTenant(tenantName);
		// @SuppressWarnings("unchecked")
		// Optional<AdministradorTenant> u = (Optional<AdministradorTenant>)
		// request.getSession()
		// .getAttribute("administradorTenant");
		// if (u == null || !u.isPresent() || !u.get().getEmail().equals(email))
		// {
		// return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		// }
		// realizacionEspectaculoService.altaRealizacionEspectaculo(realizacionEspectaculoDTO);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/* APIS PUBLICAS */

	@RequestMapping(path = "/verRealizacionesDeEspectaculo/", method = RequestMethod.GET)
	public ResponseEntity<List<RealizacionEspectaculoDTO>> verRealizacionesDeEspectaculo(
			@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "idEspectaculo", required = true) String id) {

		TenantContext.setCurrentTenant(tenantName);
		List<RealizacionEspectaculoDTO> realizaciones = realizacionEspectaculoService.verRealizacionesDeEspectaculo(id);
		return new ResponseEntity<List<RealizacionEspectaculoDTO>>(realizaciones, HttpStatus.OK);
	}

	@RequestMapping(path = "/verEspectaculoYSusRealizaciones/", method = RequestMethod.GET)
	public ResponseEntity<EspectaculoConTealizacionesDTO> verEspectaculoYSusRealizaciones(
			@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "idEspectaculo", required = true) String id) {

		TenantContext.setCurrentTenant(tenantName);
		EspectaculoConTealizacionesDTO espect = espectaculoService.FindOne(id);
		return new ResponseEntity<EspectaculoConTealizacionesDTO>(espect, HttpStatus.OK);
	}

	
	@RequestMapping(path = "/verProximosEspectaculosYSusRealizaciones/", method = RequestMethod.GET)
	public ResponseEntity<Page<EspectaculoFullDTO>> verProximosEspectaculosYRealizaciones(
			@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam(name = "_start", required = true) int start,
			@RequestParam(name = "_end", required = true) int end,
			@RequestParam(name = "sort", required = false) String sortField,
			@RequestParam(name = "order", required = false) String sortOrder,
			@RequestParam(name = "_q", required = false) String query,
			@RequestParam(name = "_q_operator", required = false) String operator) {

		TenantContext.setCurrentTenant(tenantName);
		if (StringUtils.isNotBlank(query)) {
			Page<Espectaculo> pag = this.espectaculoService
					.findAll(PageUtils.getPageRequest(start, end, sortField, sortOrder), query);
			final Page<EspectaculoFullDTO> retPag = pag.map(this::mapearContenidoDePagina);
			return new ResponseEntity<Page<EspectaculoFullDTO>>(retPag, HttpStatus.OK);
		} else {
			Page<Espectaculo> pag = this.espectaculoService
					.findAllActivos(PageUtils.getPageRequest(start, end, sortField, sortOrder));
			final Page<EspectaculoFullDTO> retPag = pag.map(this::mapearContenidoDePagina);
			return new ResponseEntity<Page<EspectaculoFullDTO>>(retPag, HttpStatus.OK);
		}
	}

	/* APIS DE USUARIO */

	@RequestMapping(path = "/comprarEntradaEspectaculo/", method = RequestMethod.GET)
	public ResponseEntity<EntradaDTO> comprarEntradaEspectaculo(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "idRealizacion", required = true) Long idRealizacion,
			@RequestParam(name = "idSector", required = true) String idSector) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
		if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		Optional<Entrada> entr = realizacionEspectaculoService.comprarEntradaEspectaculo(idRealizacion, idSector,
				email);
		if (entr.isPresent()) {
			return new ResponseEntity<EntradaDTO>(this.mapearEntrada(entr.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<EntradaDTO>(HttpStatus.GONE);
		}
	}



	/*
	 * muestra segun las preferencias del usuario (Espectaculos tipos de
	 * espectaculos y realizaciones a las que se haya susbribido)
	 */

	@RequestMapping(path = "/obtenerEspectaculosUsuario/", method = RequestMethod.GET)
	public ResponseEntity<List<EspectaculoFullDTO>> obtenerEspectaculosUsuario(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "email", required = true) String email) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
		if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
			return new ResponseEntity<List<EspectaculoFullDTO>>(HttpStatus.FORBIDDEN);
		}
		List<EspectaculoFullDTO> espectaculos = espectaculoService.obtenerEspectaculosOsuario(email);
		return new ResponseEntity<List<EspectaculoFullDTO>>(espectaculos, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/consultaDisponibilidadDeLocalidades/", method = RequestMethod.GET)
	public ResponseEntity<RealizacionEspectaculoDisponibilidadDTO> consultaDisponibilidadDeLocalidades(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, 
			@RequestParam(name = "idRealizacion", required = true) Long idRealizacion) {

		TenantContext.setCurrentTenant(tenantName);
		RealizacionEspectaculoDisponibilidadDTO reDisp = realizacionEspectaculoService.consultaDisponibilidadDeLocalidades(idRealizacion);
			return new ResponseEntity<RealizacionEspectaculoDisponibilidadDTO>(reDisp, HttpStatus.OK);
	}
	

	@RequestMapping(path = "/obtenerEspectaculos/", method = RequestMethod.GET)
	public ResponseEntity<List<EspectaculoDTO>> obtenerEspectaculos(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request) {

		TenantContext.setCurrentTenant(tenantName);
		List<EspectaculoDTO> espectaculos = espectaculoService.obtenerEspectaculos();
		return new ResponseEntity<List<EspectaculoDTO>>(espectaculos, HttpStatus.OK);
	}

	// private String crearQuery(String query) {
	// Date d = new Date();
	// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	// String date = format.format(d);
	//
	// String ret = "nombre:" + query + ",descripcion:" + query +
	// ",realizacionEspectaculo.sala.nombre:" + query
	// + ",tipoEspectaculo.nombre:" + query
	// + ",realizacionEspectaculo.fecha>" + date;
	// return ret;
	// }

	private EspectaculoFullDTO mapearContenidoDePagina(Espectaculo e) {
		return new EspectaculoFullDTO(e);
	}
	
	private EntradaDTO mapearEntrada(Entrada ent) {
		return new EntradaDTO(ent);
	}

}
