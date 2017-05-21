package com.grupo6.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.AdministradorTenant;
import com.grupo6.persistence.repository.AdministradorTenantRepository;
import com.grupo6.service.AdministradorService;

@Service
public class AdministradorServiceBean implements AdministradorService {

	@Autowired
	private AdministradorTenantRepository administradorRepository;

	@Override
	public void agregarAdministrador(AdministradorTenant dtos) {
		administradorRepository.save(dtos);
		
	}
}
