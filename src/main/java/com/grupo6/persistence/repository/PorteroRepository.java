package com.grupo6.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Portero;

@Repository
public interface PorteroRepository extends BaseRepository <Portero, Long>{

	Optional<Portero> findByCedula(String cedula);

}
