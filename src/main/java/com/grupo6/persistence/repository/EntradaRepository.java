package com.grupo6.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.Sector;
import com.grupo6.persistence.model.Usuario;

@Repository
public interface EntradaRepository extends BaseRepository <Entrada, Long>{

	Stream<Entrada> findByRealizacionEspectaculoAndSector(RealizacionEspectaculo realizacion, Sector sec);
	
	List <Entrada> findByRealizacionEspectaculoAndSectorAndUsuarioIsNull(RealizacionEspectaculo realizacion, Sector sec);
	
	@EntityGraph("Entrada.Full")
	List <Entrada> findByRealizacionEspectaculoAndUsuarioIsNotNull(RealizacionEspectaculo realizacion);
	
	@EntityGraph("Entrada.Full")
	List<Entrada> findByUsuario(Usuario usuario);
	
	@EntityGraph("Entrada.Full")
	Optional<Entrada> findOne(Long id);
}
