package com.grupo6.service.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.HistorialEntradas;
import com.grupo6.persistence.model.Usuario;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.HistorialEntradasRepository;
import com.grupo6.persistence.repository.RealizacionEspectaculoRepository;
import com.grupo6.persistence.repository.UsuarioRepository;
import com.grupo6.rest.dto.EntradaHistorialDTO;
import com.grupo6.service.UserService;

@Service
public class UserServiceBean implements UserService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private HistorialEntradasRepository historialEntradasRepository;
	
	@Autowired
	private RealizacionEspectaculoRepository realizacionEspectaculoRepository;

	@Autowired
	private EspectaculoRepository espectaculoRepository;

	
	@Override
	public Optional<Usuario> altaUsuario(Usuario dtos) {
		if (dtos.getGmailToken().isEmpty() && dtos.getGmailToken().length() < 3){
			String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(dtos.getPassword());
			dtos.setPassword(sha256hex);
		}else {
			String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(dtos.getGmailToken());
			dtos.setGmailToken(sha256hex);
		}
		if (usuarioRepository.findByEmail(dtos.getEmail()).isPresent()){
			return null; 
		}else{
			return Optional.of(usuarioRepository.save(dtos));
		}
	}

	@Override
	public Optional<Usuario> loginEmailPassword(String email, String password) {
		return usuarioRepository.findByEmail(email);

	}

	@Override
	public Optional<Usuario> altaOLoginConGmail(String id, String email) {
		Optional<Usuario> usr = usuarioRepository.findByEmail(email);
		if (usr.get().getGmailToken() == null || usr.get().getGmailToken().length() < 3) {
			usr.get().setGmailToken(id);
			usuarioRepository.save(usr.get());
		} else if (!usr.get().getGmailToken().equals(id)) {
			return Optional.empty();
		}
		return usr;
	}

	@Override
	public List<EntradaHistorialDTO> consultaHistorialCompras(String email) {
		Optional<Usuario> user = usuarioRepository.findByEmail(email);
		List <HistorialEntradas> entradas = historialEntradasRepository.findByUsuario(user);
		List<EntradaHistorialDTO> entradasDTO = new ArrayList<EntradaHistorialDTO>();
		for (HistorialEntradas ent : entradas){
			EntradaHistorialDTO entradaDTO = new EntradaHistorialDTO(ent);
			entradasDTO.add(entradaDTO);
		}
		return entradasDTO;
	}
}
