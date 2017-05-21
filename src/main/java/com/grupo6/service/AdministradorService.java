package com.grupo6.service;

import java.util.Optional;

import com.grupo6.persistence.model.AdministradorTenant;
import com.grupo6.persistence.model.Portero;
import com.grupo6.persistence.model.Vendedor;

public interface AdministradorService {

	void agregarVendedor(Vendedor vendedor);

	void agregarPortero(Portero portero);

	void agregarAdministrador(AdministradorTenant dtos);

	Optional<AdministradorTenant> login(String email);

}
