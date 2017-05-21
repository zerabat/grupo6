package com.grupo6.service;

import java.util.List;

import com.grupo6.persistence.model.Sala;

public interface SalaService {

	void altaSala(Sala sala);
	
	void modificarSala(Sala sala);
	
	List <Sala> obtenerSalas();

}
