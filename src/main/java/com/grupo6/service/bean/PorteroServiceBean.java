package com.grupo6.service.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Portero;
import com.grupo6.persistence.repository.PorteroRepository;
import com.grupo6.service.PorteroService;

@Service
public class PorteroServiceBean implements PorteroService {

	@Autowired
	PorteroRepository porteroRepository;
	
	
	@Override
	public Optional<Portero> login(String cedula) {
		return porteroRepository.findByCedula(cedula);
		
	}

}
