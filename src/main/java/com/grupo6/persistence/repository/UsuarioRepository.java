package com.grupo6.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Usuario;

@Repository
public interface UsuarioRepository extends BaseRepository <Usuario, Long>{

	Optional<Usuario> findByEmail(String email);

}
