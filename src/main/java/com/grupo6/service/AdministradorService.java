package com.grupo6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.grupo6.persistence.model.AdministradorTenant;
import com.grupo6.persistence.model.Portero;
import com.grupo6.persistence.model.Vendedor;

public interface AdministradorService {

	void agregarVendedor(Vendedor vendedor);

	void agregarPortero(Portero portero);

	void agregarAdministrador(AdministradorTenant dtos);

	Optional<AdministradorTenant> login(String email);

	List<Portero> obenerPorteros();

	void modificarPortero(Portero portero);

	void eliminarPortero(Long idPortero);

}
