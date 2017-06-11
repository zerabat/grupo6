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
		Optional<Sala> s = salaRepository.findOne(sector.getSala().getId());
		
		sector.setSala(s.get());
		sectorRepository.save(sector);
		List<Sector> sectores = sectorRepository.findBySalaId(s.get().getId());
		//actualizamos la cantidad de asientos en la sala
		int cantTotalAsientos = 0;
		for (Sector sec : sectores){
			cantTotalAsientos += sec.getCapacidad();
		}
		s.get().setTotal_localidad(cantTotalAsientos);
		salaRepository.save(s.get());
	}


	@Override
	public List<Sector> obtenerSectoresSala(String salaId) {
		
		Optional<Sala> s = salaRepository.findOne(Long.valueOf(salaId));
		List<Sector> sectores = sectorRepository.findBySalaId(Long.valueOf(salaId));
		sectores.forEach(x -> x.setSala(s.get()));
		return sectores;
		
	}


}
