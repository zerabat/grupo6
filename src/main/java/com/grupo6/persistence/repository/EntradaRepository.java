package com.grupo6.persistence.repository;

import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Entrada;

@Repository
public interface EntradaRepository extends BaseRepository <Entrada, Integer>{

	Stream<Entrada> findByRealizacionEspectaculoAndSector(Long idRealizacion, Long idSector);

}
