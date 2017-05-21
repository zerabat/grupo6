package com.grupo6.service;

import java.util.Optional;

import com.grupo6.persistence.model.Usuario;

public interface UserService {

	void altaUsuario(Usuario dtos);

	Optional<Usuario> loginEmailPassword(String email, String password);

}
