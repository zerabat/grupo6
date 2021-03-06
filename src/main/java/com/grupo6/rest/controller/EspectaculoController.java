package com.grupo6.rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.AdministradorTenant;
import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.Usuario;
import com.grupo6.rest.dto.EntradaDTO;
import com.grupo6.rest.dto.EspectaculoConTealizacionesDTO;
import com.grupo6.rest.dto.EspectaculoDTO;
import com.grupo6.rest.dto.EspectaculoFullDTO;
import com.grupo6.rest.dto.EspectaculoUsuarioDTO;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;
import com.grupo6.rest.dto.RealizacionEspectaculoDisponibilidadDTO;
import com.grupo6.rest.dto.TipoEspectaculoDTO;
import com.grupo6.service.EspectaculoService;
import com.grupo6.service.RealizacionEspectaculoService;
import com.grupo6.util.page.PageUtils;

@RestController
public class EspectaculoController {

	@Autowired
	private EspectaculoService espectaculoService;

	@Autowired
	private RealizacionEspectaculoService realizacionEspectaculoService;
	
	@Value("${imagenesPath}")
	private String imagenesPath;

	/* APIS DE ADMINISTRADOR DE UN TENANT */

	@RequestMapping(path = "/altaEspectaculo/", method = RequestMethod.POST)
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
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession().getAttribute("administradorTenant");
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

	@RequestMapping(path = "/comprarEntradaEspectaculo/", method = RequestMethod.POST)
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

	@RequestMapping(path = "/obtenerEspectaculosSugeridosUsuario/", method = RequestMethod.GET)
	public ResponseEntity<List<EspectaculoFullDTO>> obtenerEspectaculosSugeridosUsuario(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "email", required = true) String email) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
		if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
			return new ResponseEntity<List<EspectaculoFullDTO>>(HttpStatus.FORBIDDEN);
		}
		List<EspectaculoFullDTO> espectaculos = espectaculoService.obtenerEspectaculosSugeridosOsuario(email);
		return new ResponseEntity<List<EspectaculoFullDTO>>(espectaculos, HttpStatus.OK);
	}
	
	// ver los espectaculos y la realización para la cual el usuario compro entradas
	@RequestMapping(path = "/obtenerEspectaculosUsuario/", method = RequestMethod.GET)
	public ResponseEntity<List<EspectaculoUsuarioDTO>> obtenerEspectaculosUsuario(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam(name = "email", required = true) String email) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<Usuario> u = (Optional<Usuario>) request.getSession().getAttribute("usuario");
		if (u == null || !u.isPresent() || !u.get().getEmail().equals(email)) {
			return new ResponseEntity<List<EspectaculoUsuarioDTO>>(HttpStatus.FORBIDDEN);
		}
		List<EspectaculoUsuarioDTO> espectaculos = espectaculoService.obtenerEspectaculosOsuario(email);
		return new ResponseEntity<List<EspectaculoUsuarioDTO>>(espectaculos, HttpStatus.OK);
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
//		@SuppressWarnings("unchecked")
//		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
//				.getAttribute("administradorTenant");
//		if (a == null || !a.isPresent() || !a.get().getEmail().equals(adminEmail)) {
//			return new ResponseEntity<List<EspectaculoDTO>>(HttpStatus.FORBIDDEN);
//		}
		List<EspectaculoDTO> espectaculos = espectaculoService.obtenerEspectaculos();
		return new ResponseEntity<List<EspectaculoDTO>>(espectaculos, HttpStatus.OK);
	}

	private EspectaculoFullDTO mapearContenidoDePagina(Espectaculo e) {
		EspectaculoFullDTO eFDTO = new EspectaculoFullDTO(e);
//		eFDTO.setImagenesEspectaculo(espectaculoService.obtenerImagenesEspectaculo(e.getId()));
		
		String pathImagen = imagenesPath + "\\" + TenantContext.getCurrentTenant() + "\\" + String.valueOf(e.getId()) + "\\";
		File[] files = Paths.get(pathImagen).toFile().listFiles();
		List <String> ret = new ArrayList<String>();
		FileInputStream fileInputStreamReader = null; 
		for(File f: files){
			try{
			Path path = Paths.get(f.getAbsolutePath());
			byte[] data = Files.readAllBytes(path);
			fileInputStreamReader = new FileInputStream(f);
			fileInputStreamReader.read(data);
			String encodedFile = Base64.getEncoder().encodeToString(data);
			ret.add(encodedFile);
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		eFDTO.setImagenesEspectaculoString(ret); 
		
		return eFDTO;
	}
	
	private EntradaDTO mapearEntrada(Entrada ent) {
		return new EntradaDTO(ent);
	}

}
