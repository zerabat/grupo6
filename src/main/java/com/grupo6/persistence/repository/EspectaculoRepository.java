package com.grupo6.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Espectaculo;

@Repository
public interface EspectaculoRepository extends BaseRepository <Espectaculo, Long>{

	@EntityGraph("Espectaculo.Full")
	@Override
	List<Espectaculo> findAll();
	
	@EntityGraph("Espectaculo.Full")
	Page<Espectaculo> findAll(Specification<Espectaculo> entradaSpecification, Pageable pageRequest);
}
