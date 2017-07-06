package com.grupo6.rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.AdministradorTenant;

@RestController
public class ImagenesController {

	@Value("${imagenesPath}")
	private String imagenesPath;

	@Value("${imagenesSalaPath}")
	private String imagenesSalaPath;

	@PostMapping("/subirImagenEspectaculo/")
	public ResponseEntity<String> agregarImagenAEvento(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam("file") MultipartFile file,
			@RequestParam("email") String adminEmail, @RequestParam("espectaculoId") Long espectaculoId) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(adminEmail)) {
			new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		if (file.isEmpty()) {
			new ResponseEntity<String>("debe subir una imagen", HttpStatus.NO_CONTENT);
		}

		try {
			String pathImagen = imagenesPath + "\\" + tenantName + "\\" + String.valueOf(espectaculoId) + "\\";
			byte[] bytes = file.getBytes();
			File espectaculoPath = new File(pathImagen);
			if (!espectaculoPath.exists()) {
				espectaculoPath.mkdirs();
			}
			Path path = Paths.get(pathImagen + file.getOriginalFilename());
			Files.write(path, bytes);
			String ret = "Se subió exitosamente la imgen '" + file.getOriginalFilename() + "'" + "asociada al evento: ";
			return new ResponseEntity<String>(ret, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/eliminarImagensEspectaculo/")
	public String eliminarImagensEspectaculo(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam("email") String adminEmail, @RequestParam("enventoId") Long enventoId) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(adminEmail)) {
			return "El email es incorrecto o el administrador no inició la sesión";
		}

		String pathImagen = imagenesPath + "\\" + tenantName + "\\" + String.valueOf(enventoId) + "\\";
		File espectaculoPath = new File(pathImagen);
		if (espectaculoPath.exists()) {
			ImagenesController.deleteFolder(espectaculoPath);
		}

		return "ok";

	}

	@ResponseBody
	@RequestMapping(value = "/obtenerImagenesEspectaculo/", method = RequestMethod.GET /*, produces = MediaType.IMAGE_JPEG_VALUE*/)
	public List<byte[]> obtenerImagenesEspectaculo(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			 @RequestParam("espectaculoId") Long espectaculoId) throws IOException {

		String pathImagen = imagenesPath + "\\" + tenantName + "\\" + String.valueOf(espectaculoId) + "\\";
		File[] files = Paths.get(pathImagen).toFile().listFiles();
		List <byte[]> ret = new ArrayList<byte[]>();
		for(File f: files){
			Path path = Paths.get(f.getAbsolutePath());
			byte[] data = Files.readAllBytes(path);
			ret.add(data);
		}
		return ret;

	}
	
	@ResponseBody
	@RequestMapping(value = "/obtenerImagenesEspectaculoString/", method = RequestMethod.GET /*, produces = MediaType.IMAGE_JPEG_VALUE*/)
	public List<String> obtenerImagenesEspectaculoString(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam("espectaculoId") Long espectaculoId) throws IOException {

		String pathImagen = imagenesPath + "\\" + tenantName + "\\" + String.valueOf(espectaculoId) + "\\";
		File[] files = Paths.get(pathImagen).toFile().listFiles();
		List <String> ret = new ArrayList<String>();
		FileInputStream fileInputStreamReader = null; 
		for(File f: files){
			Path path = Paths.get(f.getAbsolutePath());
			byte[] data = Files.readAllBytes(path);
			fileInputStreamReader = new FileInputStream(f);
			fileInputStreamReader.read(data);
			String encodedFile = Base64.getEncoder().encodeToString(data);
			ret.add(encodedFile);
		}
		return ret;

	}
	
	@PostMapping("/subirImagenSala/")
	public ResponseEntity<String> subirImagenSala(@RequestHeader("X-TenantID") String tenantName,
			HttpServletRequest request, @RequestParam("file") MultipartFile file,
			@RequestParam("email") String adminEmail, @RequestParam("salaId") Long salaId) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(adminEmail)) {
			new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		if (file.isEmpty()) {
			new ResponseEntity<String>("debe subir una imagen", HttpStatus.NO_CONTENT);
		}

		try {
			String pathImagen = imagenesSalaPath + "\\" + tenantName + "\\" + String.valueOf(salaId) + "\\";
			File convFile = new File(
					pathImagen + "sala.jpg" );
			File espectaculoPath = new File(pathImagen);
			if (!espectaculoPath.exists()) {
				espectaculoPath.mkdirs();
			}
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			String ret = "Se subió exitosamente la imgen '" + file.getOriginalFilename() + "'" + "asociada al evento: ";
			return new ResponseEntity<String>(ret, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/eliminarImagenSala/")
	public Object eliminarImagenSala(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam("email") String adminEmail, @RequestParam("salaId") Long salaId ) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(adminEmail)) {
			return "El email es incorrecto o el administrador no inició la sesión";
		}

		String pathImagen = imagenesSalaPath + "\\" + tenantName + "\\" + String.valueOf(salaId) + "\\";
		File espectaculoPath = new File(pathImagen);
		if (espectaculoPath.exists()) {
			ImagenesController.deleteFolder(espectaculoPath);
		}
		return "sala eliminada correctamente";
	}


	@ResponseBody
	@RequestMapping(value = "/obtenerImagenSala/", method = RequestMethod.GET /*, produces = MediaType.IMAGE_JPEG_VALUE*/)
	public byte[] obtenerImagensala(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
		@RequestParam("salaId") Long salaId) throws IOException {

		String pathImagen = imagenesSalaPath + "\\" + tenantName + "\\" + String.valueOf(salaId) + "\\";
		Path path = Paths.get(pathImagen + "sala.jpg");
		byte[] data = Files.readAllBytes(path);
		return data;

	}

	@ResponseBody
	@RequestMapping(value = "/obtenerImagenSalaString/", method = RequestMethod.GET /*, produces = MediaType.IMAGE_JPEG_VALUE*/)
	public List<String> obtenerImagensalaString(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			 @RequestParam("salaId") Long salaId) throws IOException {

		String pathImagen = imagenesSalaPath + "\\" + tenantName + "\\" + String.valueOf(salaId) + "\\";
		File[] files = Paths.get(pathImagen).toFile().listFiles();
		List <String> ret = new ArrayList<String>();
		FileInputStream fileInputStreamReader = null; 
		for(File f: files){
			Path path = Paths.get(f.getAbsolutePath());
			byte[] data = Files.readAllBytes(path);
			fileInputStreamReader = new FileInputStream(f);
			fileInputStreamReader.read(data);
			String encodedFile = Base64.getEncoder().encodeToString(data);
			ret.add(encodedFile);
		}
		return ret;
		

	}

	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}
}
