package com.grupo6.persistence.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.Sector;

@Repository
public interface EntradaRepository extends BaseRepository <Entrada, Integer>{

	Stream<Entrada> findByRealizacionEspectaculoAndSector(RealizacionEspectaculo realizacion, Sector sec);
	
	List <Entrada> findByRealizacionEspectaculoAndSectorAndUsuarioIsNull(RealizacionEspectaculo realizacion, Sector sec);
}
