package com.grupo6.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.RealizacionEspectaculo;
import com.grupo6.persistence.model.SuscripcionEspectaculo;
import com.grupo6.persistence.model.TipoEspectaculo;
import com.grupo6.persistence.model.Usuario;

@Repository
public interface SuscripcionEspectaculoRepository extends BaseRepository <SuscripcionEspectaculo, Long>{

	Optional<SuscripcionEspectaculo> findByUsuarioAndTipoEspectaculo(Usuario u, TipoEspectaculo te);

	Optional<SuscripcionEspectaculo> findByUsuarioAndEspectaculo(Usuario u, Espectaculo e);

	Optional<SuscripcionEspectaculo> findByUsuarioAndRealizacionEspectaculo(Usuario u, RealizacionEspectaculo re);

	List<SuscripcionEspectaculo> findByEspectaculo(Espectaculo e);
	
	List<SuscripcionEspectaculo> findByRealizacionEspectaculo(RealizacionEspectaculo re);

	List<SuscripcionEspectaculo> findByUsuario(Usuario usuario);

}
