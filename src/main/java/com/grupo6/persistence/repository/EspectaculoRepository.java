package com.grupo6.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.Espectaculo;

@Repository
public interface EspectaculoRepository extends BaseRepository <Espectaculo, Long>{

	@EntityGraph("Espectaculo.Full")
	@Override
	List<Espectaculo> findAll();
	
	@EntityGraph("Espectaculo.Full")
	Page<Espectaculo> findAll(Specification<Espectaculo> entradaSpecification, Pageable pageRequest);
	
	@EntityGraph("Espectaculo.Full")
	@Query ("SELECT e FROM Espectaculo e "
			+ " JOIN  e.realizacionEspectaculo r "
			+ " JOIN  e.tipoEspectaculo te"
			+ " JOIN  r.sala s "
			+ " WHERE r.espectaculo = e and ( e.nombre like %:busqueda% or s.nombre like %:busqueda% or e.descripcion like %:busqueda%) and r.fecha > :hoy")
	Page<Espectaculo> findAllWithSearh(Pageable pageRequest, @Param("busqueda") String busqueda, @Param("hoy")  Date d);
	
	@EntityGraph("Espectaculo.Full")
	@Query ("SELECT e FROM Espectaculo e "
			+ " join e.realizacionEspectaculo r "
			+ " join e.tipoEspectaculo te"
			+ " join r.sala s "
			+ " WHERE r.espectaculo = e and  r.fecha > :hoy")
	Page<Espectaculo> findAllActivos(Pageable pageRequest, @Param("hoy")  Date d);

	@EntityGraph("Espectaculo.Full")
	@Query ("SELECT e FROM Espectaculo e "
			+ " join e.realizacionEspectaculo r "
			+ " join e.tipoEspectaculo te"
			+ " join r.sala s "
			+ " WHERE r.espectaculo = e and  r.fecha > :hoy")
	List<Espectaculo> findAllActivos(@Param("hoy")  Date d);

	@EntityGraph("Espectaculo.Full")
	@Query ("SELECT e FROM Espectaculo e "
			+ " JOIN  e.realizacionEspectaculo r "
			+ " JOIN  e.tipoEspectaculo te"
			+ " JOIN  r.sala s "
			+ " WHERE r.espectaculo = e  and r.fecha > :hoy and te.id = :idTipoEspec")
	List<Espectaculo> finBytipoEspectaculo(@Param("idTipoEspec") long idTipoEspec, @Param("hoy")  Date d);
	
	@EntityGraph("Espectaculo.Full")
	@Query ("SELECT e FROM Espectaculo e "
			+ " JOIN  e.realizacionEspectaculo r "
			+ " JOIN  e.tipoEspectaculo te"
			+ " JOIN  r.sala s "
			+ " WHERE r.espectaculo = e  and r.fecha > :hoy and e.id = :idEspec")
	Espectaculo findOneActive(@Param("idEspec") long idEspec, @Param("hoy")  Date d);
	
	@EntityGraph("Espectaculo.Full")
	Espectaculo findByNombre(String nombre);
	
	
}
