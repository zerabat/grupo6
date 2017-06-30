package com.grupo6.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.HistorialEntradas;
import com.grupo6.persistence.model.Usuario;

@Repository
public interface HistorialEntradasRepository extends BaseRepository <HistorialEntradas, Long>{

	List<HistorialEntradas> findByUsuario(Optional<Usuario> user);

	Optional<HistorialEntradas> findByEntrada(Entrada entrada);

}
