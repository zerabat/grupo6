package com.grupo6.rest.dto;

import com.grupo6.persistence.model.TipoEspectaculo;

public class TipoEspectaculoDTO {

	private long id;
	private String nombre;
	
	public TipoEspectaculoDTO(TipoEspectaculo te) {
		this.id = te.getId();
		this.nombre = te.getNombre();
	}
	public TipoEspectaculoDTO() {
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
	
	
}
