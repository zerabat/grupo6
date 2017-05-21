package com.grupo6.service.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.AdministradorTenant;
import com.grupo6.persistence.model.Portero;
import com.grupo6.persistence.model.Vendedor;
import com.grupo6.persistence.repository.AdministradorTenantRepository;
import com.grupo6.persistence.repository.PorteroRepository;
import com.grupo6.persistence.repository.VendedorRepository;
import com.grupo6.service.AdministradorService;

@Service
public class AdministradorServiceBean implements AdministradorService {

	@Autowired
	private AdministradorTenantRepository administradorRepository;

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private PorteroRepository porteroRepository;

	@Override
	public void agregarAdministrador(AdministradorTenant dtos) {
		administradorRepository.save(dtos);

	}

	@Override
	public void agregarVendedor(Vendedor vendedor) {
		vendedorRepository.save(vendedor);
	}

	@Override
	public void agregarPortero(Portero portero) {
		porteroRepository.save(portero);
		
	}

	@Override
	public Optional<AdministradorTenant> login(String email) {
		return administradorRepository.findByEmail(email);
	}
}
