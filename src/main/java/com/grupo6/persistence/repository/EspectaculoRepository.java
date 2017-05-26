package com.grupo6.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Espectaculo;

@Repository
public interface EspectaculoRepository extends BaseRepository <Espectaculo, Integer>{

	@EntityGraph("Espectaculo.Full")
	@Override
	List<Espectaculo> findAll();
}
