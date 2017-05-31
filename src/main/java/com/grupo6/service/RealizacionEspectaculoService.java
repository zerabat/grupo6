package com.grupo6.service;

import java.util.List;

import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;

public interface RealizacionEspectaculoService {

	void altaRealizacionEspectaculo(RealizacionEspectaculoDTO realizacionEspectaculoDTO);

	List<RealizacionEspectaculoDTO> verRealizacionesDeEspectaculo(String id);

}
