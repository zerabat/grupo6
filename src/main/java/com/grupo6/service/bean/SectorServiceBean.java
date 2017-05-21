package com.grupo6.service.bean;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Sala;
import com.grupo6.persistence.model.Sector;
import com.grupo6.persistence.repository.SalaRepository;
import com.grupo6.persistence.repository.SectorRepository;
import com.grupo6.service.SectorService;

@Service
public class SectorServiceBean implements SectorService {

	@Autowired
	private SectorRepository sectorRepository;
	
	@Autowired
	private SalaRepository salaRepository;


	@Override
	public void altaSector(Sector sector) {
		sectorRepository.save(sector);		
	}


	@Override
	public List<Sector> obtenerSectoresSala(String salaId) {
		Optional<Sala> s = salaRepository.findOne(Integer.valueOf(salaId));
		sectorRepository.findBySala(s.get());
		return null;
	}


}
