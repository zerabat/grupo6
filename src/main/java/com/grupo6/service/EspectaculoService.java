package com.grupo6.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.rest.dto.EspectaculoDTO;
import com.grupo6.rest.dto.EspectaculoFullDTO;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;

public interface EspectaculoService {

	void agregarEspectaculo(EspectaculoDTO espectaculo);

	List<EspectaculoDTO> obtenerEspectaculos();

	Page<Espectaculo> findAll(Pageable pageRequest);

	Page<Espectaculo> findAll(Specification<Espectaculo> entradaSpecification, Pageable pageRequest);

	Page<Espectaculo> findAll(Pageable pageRequest, String busqueda);

	Page<Espectaculo> findAllActivos(Pageable pageRequest);

	void modificarEspectaculo(EspectaculoDTO espDTO);

	void desSuscribirseTipoEspectaculo(Long idTipoEspectaculo, String email);

	void suscribirseTipoEspectaculo(Long idTipoEspectaculo, String email);

	void desSuscribirseAEspectaculo(Long idEspectaculo, String email);

	void suscribirseAEspectaculo(Long idEspectaculo, String email);

	List<EspectaculoFullDTO> obtenerEspectaculosOsuario(String email);

	EspectaculoFullDTO FindOne(String id);
}
