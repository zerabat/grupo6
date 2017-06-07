package com.grupo6.service;

import java.util.Optional;

import com.grupo6.persistence.model.Usuario;

public interface UserService {

	Optional<Usuario> altaUsuario(Usuario dtos);

	Optional<Usuario> loginEmailPassword(String email, String password);
	
	Optional<Usuario> altaOLoginConGmail(String id, String email);

}
