package com.grupo6.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Usuario;
import com.grupo6.persistence.repository.UsuarioRepository;
import com.grupo6.service.UserService;

@Service
public class UserServiceBean implements UserService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void altaUsuario(Usuario dtos) {
		usuarioRepository.save(dtos);
		
	}
}
