package com.grupo6.service.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.Sala;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.SalaRepository;
import com.grupo6.rest.dto.EspectaculoDTO;
import com.grupo6.service.EspectaculoService;

@Service
public class EspectaculoServiceBean implements EspectaculoService {

	@Autowired
	private EspectaculoRepository espectaculoRepository;

	@Autowired 
	private SalaRepository salarepository;
	
	@Override
	public void agregarEspectaculo(Espectaculo espectaculo) {
		
		Optional<Sala> s = salarepository.findOne(espectaculo.getRealizacionEspectaculo().stream().findFirst().get().getSala().getId());
		espectaculo.getRealizacionEspectaculo().stream().forEach(x -> {
			x.setSala(s.get());
			x.setEspectaculo(espectaculo);
		});
		espectaculoRepository.save(espectaculo);
	}


	@Override
	public void modificarEspectaculo(Espectaculo espectaculo) {
		espectaculoRepository.save(espectaculo);		
	}


	@Override
	public List<EspectaculoDTO> obtenerEspectaculos() {
		List<EspectaculoDTO> lDTO = new ArrayList<EspectaculoDTO>();
		
		espectaculoRepository.findAll().forEach(x -> {
			EspectaculoDTO eDTO = new EspectaculoDTO(x);
			lDTO.add(eDTO);
		});
		return lDTO;
	}
	
	

}
