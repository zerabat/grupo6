package com.grupo6.service;

import java.util.List;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.rest.dto.EspectaculoDTO;

public interface EspectaculoService {

	void agregarEspectaculo(EspectaculoDTO espectaculo);

	void modificarEspectaculo(Espectaculo espectaculo);

	List<EspectaculoDTO> obtenerEspectaculos();
}
