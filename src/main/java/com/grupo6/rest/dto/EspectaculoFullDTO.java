package com.grupo6.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.TipoEspectaculo;

public class EspectaculoFullDTO {

	private long id;

	private String nombre;
	
	private String descripcion;
	
	private TipoEspectaculo tipoEspectaculo;

	private List<RealizacionEspectaculoFullDTO> realizacionEspectaculo;
	
	
	public EspectaculoFullDTO(Espectaculo e){
		this.id = e.getId();
		this.nombre = e.getNombre();
		this.descripcion = e.getDescripcion();
		this.tipoEspectaculo  = e.getTipoEspectaculo();
		this.realizacionEspectaculo = new ArrayList<RealizacionEspectaculoFullDTO>();
		e.getRealizacionEspectaculo().stream().forEach(r -> {
			RealizacionEspectaculoFullDTO reFDTO = new RealizacionEspectaculoFullDTO(r); 
			this.realizacionEspectaculo.add(reFDTO);
		});
	}
	

	public EspectaculoFullDTO(){
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public TipoEspectaculo getTipoEspectaculo() {
		return tipoEspectaculo;
	}


	public void setTipoEspectaculo(TipoEspectaculo tipoEspectaculo) {
		this.tipoEspectaculo = tipoEspectaculo;
	}


	public List<RealizacionEspectaculoFullDTO> getRealizacionEspectaculo() {
		return realizacionEspectaculo;
	}


	public void setRealizacionEspectaculo(List<RealizacionEspectaculoFullDTO> realizacionEspectaculo) {
		this.realizacionEspectaculo = realizacionEspectaculo;
	}

	
}
