package com.grupo6.service.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.TipoEspectaculo;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.SalaRepository;
import com.grupo6.persistence.repository.TipoEspectaculoRepository;
import com.grupo6.rest.dto.EspectaculoDTO;
import com.grupo6.service.EspectaculoService;

@Service
public class EspectaculoServiceBean implements EspectaculoService {

	@Autowired
	private EspectaculoRepository espectaculoRepository;

	@Autowired 
	private SalaRepository salarepository;
	
	@Autowired
	private TipoEspectaculoRepository tipoEspectaculoRepository;
	
	@Override
	public void agregarEspectaculo(EspectaculoDTO espectaculo) {
		Espectaculo e = new Espectaculo();
		e.setDescripcion(espectaculo.getDescripcion());
		e.setNombre(espectaculo.getNombre());
		Optional <TipoEspectaculo>  tE = tipoEspectaculoRepository.findOne(espectaculo.getIdTipoEspectaculo());
//		if (!tE.isPresent()){
//			return null;
//		}
		e.setTipoEspectaculo(tE.get());
		espectaculoRepository.save(e);
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


	@Override
	public Page<Espectaculo> findAll(Pageable pageRequest) {
		return this.espectaculoRepository.findAll(pageRequest);
	}


	@Override
	public Page<Espectaculo> findAll(Specification<Espectaculo> entradaSpecification, Pageable pageRequest) {
		return this.espectaculoRepository.findAll(entradaSpecification, pageRequest);
		
	}
	
	@Override
	public Page<Espectaculo> findAll(Pageable pageRequest, String busqueda) {
		Date d = new Date();
		return this.espectaculoRepository.findAllWithSearh(pageRequest,busqueda,d);
	}

	@Override
	public Page<Espectaculo> findAllActivos(Pageable pageRequest) {
		Date d = new Date();
		return this.espectaculoRepository.findAllActivos(pageRequest,d);
	}
	
	

}
