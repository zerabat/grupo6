package com.grupo6.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.RealizacionEspectaculo;

@Repository
public interface RealizacionEspectaculoRepository extends BaseRepository <RealizacionEspectaculo, Long>{

	List<RealizacionEspectaculo> findByEspectaculoAndFechaAfter(Espectaculo espectaculo, Date d);

	List<RealizacionEspectaculo> findByFechaAfter(Date d);
}
