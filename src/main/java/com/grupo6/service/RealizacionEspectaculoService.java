package com.grupo6.service;

import java.util.List;
import java.util.Optional;

import com.grupo6.persistence.model.Entrada;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;

public interface RealizacionEspectaculoService {

	void altaRealizacionEspectaculo(RealizacionEspectaculoDTO realizacionEspectaculoDTO);

	List<RealizacionEspectaculoDTO> verRealizacionesDeEspectaculo(String id);

	Optional<Entrada> comprarEntradaEspectaculo(Long idRealizacion, String idSector, String email);

}
