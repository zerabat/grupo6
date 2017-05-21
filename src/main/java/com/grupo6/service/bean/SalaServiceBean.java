package com.grupo6.service.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Sala;
import com.grupo6.persistence.repository.SalaRepository;
import com.grupo6.service.SalaService;

@Service
public class SalaServiceBean implements SalaService {

	@Autowired
	private SalaRepository salaRepository;

	@Override
	public void altaSala(Sala sala) {
		salaRepository.save(sala);
	}

	@Override
	public void modificarSala(Sala sala) {
		salaRepository.save(sala);
		
	}

	@Override
	public List<Sala> obtenerSalas() {
		return salaRepository.findAll();
	}

}
