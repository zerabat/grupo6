package com.grupo6.service;

import java.util.List;
import java.util.Optional;

import com.grupo6.persistence.model.Usuario;
import com.grupo6.rest.dto.EntradaHistorialDTO;

public interface UserService {

	Optional<Usuario> altaUsuario(Usuario dtos);

	Optional<Usuario> loginEmailPassword(String email, String password);
	
	Optional<Usuario> altaOLoginConGmail(String id, String email);

	List<EntradaHistorialDTO> consultaHistorialCompras(String email);

}
