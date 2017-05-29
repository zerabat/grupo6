package com.grupo6.service.bean;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Entrada;
import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.Sala;
import com.grupo6.persistence.model.Sector;
import com.grupo6.persistence.repository.EntradaRepository;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.RealizacionEspectaculoRepository;
import com.grupo6.persistence.repository.SalaRepository;
import com.grupo6.persistence.repository.SectorRepository;
import com.grupo6.rest.dto.RealizacionEspectaculoDTO;
import com.grupo6.service.RealizacionEspectaculoService;

@Service
public class RealizacionEspectaculoServiceBean implements RealizacionEspectaculoService {

	@Autowired
	SalaRepository salaRepository;
	
	@Autowired
	EspectaculoRepository espectaculoRepository;
	
	@Autowired
	RealizacionEspectaculoRepository realizacionEspectaculoRepository;
	
	@Autowired
	SectorRepository sectorRepository;
	
	@Autowired
	EntradaRepository entradaRepository; 
	
	@Override
	public void altaRealizacionEspectaculo(RealizacionEspectaculoDTO realizacionEspectaculoDTO) {
		// obtengo sala y espectaculo por los id del dto y guardo la realización en la base
		Optional<Sala> s = salaRepository.findOne(realizacionEspectaculoDTO.getIdSala());
		Optional<Espectaculo> e = espectaculoRepository.findOne(realizacionEspectaculoDTO.getIdEspectaculo());
		RealizacionEspectaculo re = new RealizacionEspectaculo();
		re.setFecha(realizacionEspectaculoDTO.getFecha());
		re.setId(realizacionEspectaculoDTO.getId());
		re.setEspectaculo(e.get());
		re.setSala(s.get());
		realizacionEspectaculoRepository.save(re);
		// genero todas las entradas de acuerdo a la capacidad de los sectores de las salas
		List<Sector> sectores = sectorRepository.findBySalaId(realizacionEspectaculoDTO.getIdSala());
		sectores.stream().forEach( sector ->{
			for (int i=0; i<sector.getCapacidad(); i++){
				Entrada entrada = new Entrada ();
				entrada.setEspectaculo(e.get());
				entrada.setNumeroAsiento(i);
				// hay que pasar los precios por sector
				entrada.setPrecio(0);
				entrada.setRealizacionEspectaculo(re);
				entrada.setSector(sector);
				entradaRepository.save(entrada);
			}
		});
		
		
	}


}
