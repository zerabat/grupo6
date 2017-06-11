package com.grupo6.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.rest.dto.EspectaculoDTO;

public interface EspectaculoService {

	void agregarEspectaculo(EspectaculoDTO espectaculo);

	void modificarEspectaculo(Espectaculo espectaculo);

	List<EspectaculoDTO> obtenerEspectaculos();

	Page<Espectaculo> findAll(Pageable pageRequest);

	Page<Espectaculo> findAll(Specification<Espectaculo> entradaSpecification, Pageable pageRequest);
}
