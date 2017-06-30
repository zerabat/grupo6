package com.grupo6.service;

import java.util.Optional;

import com.grupo6.persistence.model.Portero;

public interface PorteroService {

	Optional<Portero> login(String cedula);

}
