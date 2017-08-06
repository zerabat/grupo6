package com.grupo6.service;

import java.util.List;
import java.util.Optional;

import com.grupo6.persistence.model.Entrada;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;
import com.grupo6.rest.dto.RealizacionEspectaculoDisponibilidadDTO;
import com.grupo6.rest.dto.SuscripcionEspectaculoDTO;

public interface RealizacionEspectaculoService {

	void altaRealizacionEspectaculo(RealizacionEspectaculoDTO realizacionEspectaculoDTO);

	List<RealizacionEspectaculoDTO> verRealizacionesDeEspectaculo(String id);

	Optional<Entrada> comprarEntradaEspectaculo(Long idRealizacion, String idSector, String email);

	void desSuscribirse(Long idTrealizacionEspectaculo, String email);

	void suscribirse(Long idRealizacionEspectaculo, String email);

	RealizacionEspectaculoDisponibilidadDTO consultaDisponibilidadDeLocalidades(Long idRealizacion);

	List<SuscripcionEspectaculoDTO> verSuscripcionUsuario(String email);

	Boolean verificarQR(String sha256hex, String email, String idEspectaculo);

}
