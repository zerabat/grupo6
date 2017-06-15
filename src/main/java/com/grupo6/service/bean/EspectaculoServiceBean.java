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
import com.grupo6.persistence.model.SuscripcionEspectaculo;
import com.grupo6.persistence.model.TipoEspectaculo;
import com.grupo6.persistence.model.Usuario;
import com.grupo6.persistence.repository.EspectaculoRepository;
import com.grupo6.persistence.repository.SalaRepository;
import com.grupo6.persistence.repository.SuscripcionEspectaculoRepository;
import com.grupo6.persistence.repository.TipoEspectaculoRepository;
import com.grupo6.persistence.repository.UsuarioRepository;
import com.grupo6.rest.dto.EspectaculoDTO;
import com.grupo6.rest.dto.TipoEspectaculoDTO;
import com.grupo6.service.EspectaculoService;

@Service
public class EspectaculoServiceBean implements EspectaculoService {

	@Autowired
	private EspectaculoRepository espectaculoRepository;

	@Autowired
	private TipoEspectaculoRepository tipoEspectaculoRepository;

	@Autowired
	private SuscripcionEspectaculoRepository suscripcionEspectaculoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void agregarEspectaculo(EspectaculoDTO espectaculo) {
		Espectaculo e = new Espectaculo();
		e.setDescripcion(espectaculo.getDescripcion());
		e.setNombre(espectaculo.getNombre());
		for (TipoEspectaculoDTO te : espectaculo.getTipoEspectaculo()) {
			Optional<TipoEspectaculo> tE = tipoEspectaculoRepository.findOne(te.getId());
			e.getTipoEspectaculo().add(tE.get());
		}
		espectaculoRepository.save(e);
	}

	@Override
	public void modificarEspectaculo(EspectaculoDTO espDTO) {
		Espectaculo esp = new Espectaculo();
		esp.setDescripcion(espDTO.getDescripcion());
		esp.setId(espDTO.getId());
		esp.setNombre(espDTO.getNombre());
		for (TipoEspectaculoDTO te : espDTO.getTipoEspectaculo()) {
			Optional<TipoEspectaculo> tE = tipoEspectaculoRepository.findOne(te.getId());
			esp.getTipoEspectaculo().add(tE.get());
		}
		espectaculoRepository.save(esp);
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
		return this.espectaculoRepository.findAllWithSearh(pageRequest, busqueda, d);
	}

	@Override
	public Page<Espectaculo> findAllActivos(Pageable pageRequest) {
		Date d = new Date();
		return this.espectaculoRepository.findAllActivos(pageRequest, d);
	}

	@Override
	public List<EspectaculoDTO> obtenerEspectaculosOsuario(String email) {
		// TODO si se suscribio a un Espectaculo se le va a mostrar todas las
		// futuras realizaciones de ese espectaculo si se suscribió a una
		// realizacion de un espectaculo solo se le va a mostrar la realizacion
		// a la que se suscribio si se susbribió a un tipo de espectaculo se le
		// va a mostrar espectaculos de ese tipo con sus realizaciones. El orden
		// es el siguiente: suscripcion a una realizacion suscripcion a un
		// espectaculo suscripcion a un tipo de espectaculo
		return null;
	}

	@Override
	public void desSuscribirseTipoEspectaculo(Long idTipoEspectaculo, String email) {
		Usuario u = usuarioRepository.findByEmail(email).get();
		TipoEspectaculo te = tipoEspectaculoRepository.findOne(idTipoEspectaculo).get();
		Optional<SuscripcionEspectaculo> se = suscripcionEspectaculoRepository.findByUsuarioAndTipoEspectaculo(u, te);
		if (se.isPresent()) {
			suscripcionEspectaculoRepository.delete(se.get());
		} else {
			// no existia la suscripcion
		}

	}

	@Override
	public void suscribirseTipoEspectaculo(Long idTipoEspectaculo, String email) {
		Usuario u = usuarioRepository.findByEmail(email).get();
		TipoEspectaculo te = tipoEspectaculoRepository.findOne(idTipoEspectaculo).get();
		Optional<SuscripcionEspectaculo> se = suscripcionEspectaculoRepository.findByUsuarioAndTipoEspectaculo(u, te);
		if (!se.isPresent()) {
			SuscripcionEspectaculo s = new SuscripcionEspectaculo();
			s.setTipoEspectaculo(te);
			s.setUsuario(u);
			s.setFecha(new Date());
			suscripcionEspectaculoRepository.save(s);
		} else {
			// ya existe la suscripción no hago nada
		}

	}

	@Override
	public void desSuscribirseAEspectaculo(Long idEspectaculo, String email) {
		Usuario u = usuarioRepository.findByEmail(email).get();
		Espectaculo e = espectaculoRepository.findOne(idEspectaculo).get();
		Optional<SuscripcionEspectaculo> se = suscripcionEspectaculoRepository.findByUsuarioAndEspectaculo(u, e);
		if (se.isPresent()) {
			suscripcionEspectaculoRepository.delete(se.get());
		} else {
			// no existia la suscripcion
		}

	}

	@Override
	public void suscribirseAEspectaculo(Long idEspectaculo, String email) {
		Usuario u = usuarioRepository.findByEmail(email).get();
		Espectaculo e = espectaculoRepository.findOne(idEspectaculo).get();
		Optional<SuscripcionEspectaculo> se = suscripcionEspectaculoRepository.findByUsuarioAndEspectaculo(u, e);
		if (!se.isPresent()) {
			SuscripcionEspectaculo s = new SuscripcionEspectaculo();
			s.setUsuario(u);
			s.setEspectaculo(e);
			s.setFecha(new Date());
			suscripcionEspectaculoRepository.save(s);
		} else {
			// ya existia la suscripcion
		}

	}

}
