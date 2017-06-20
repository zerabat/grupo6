package com.grupo6.rest.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo6.config.TenantContext;
import com.grupo6.persistence.model.AdministradorTenant;

@Controller
public class ImagenesController {

	@Value("${imagenesPath}")
	private String imagenesPath;

	@PostMapping("/subirImagenEspectaculo/")
	public ResponseEntity<String>  agregarImagenAEvento(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam("file") MultipartFile file, @RequestParam("email") String adminEmail,
			@RequestParam("espectaculoId") Long espectaculoId) {

		TenantContext.setCurrentTenant(tenantName);
		@SuppressWarnings("unchecked")
		Optional<AdministradorTenant> a = (Optional<AdministradorTenant>) request.getSession()
				.getAttribute("administradorTenant");
		if (a == null || !a.isPresent() || !a.get().getEmail().equals(adminEmail)) {
			new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		if (file.isEmpty()) {
			new ResponseEntity<String>("debe subir una imagen",HttpStatus.NO_CONTENT);
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
			return new ResponseEntity<String>(ret,HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/eliminarImagensEspectaculo/")
	public String eliminarImagensEspectaculo(@RequestHeader("X-TenantID") String tenantName, HttpServletRequest request,
			@RequestParam("email") String adminEmail, @RequestParam("enventoId") Long enventoId,
			RedirectAttributes redirectAttributes) {

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

		return "redirect:uploadStatus";

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
