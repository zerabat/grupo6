package com.grupo6.rest.dto;

import com.grupo6.persistence.model.Espectaculo;

public class EspectaculoDTO {

	private long id;

	private String nombre;
	
	private String descripcion;
	
	private long  idTipoEspectaculo;

	public EspectaculoDTO(Espectaculo e){
		this.id = e.getId();
		this.nombre = e.getNombre();
		this.descripcion = e.getDescripcion();
		if (e.getTipoEspectaculo() != null)
		this.idTipoEspectaculo  = e.getTipoEspectaculo().getId();
	}
	

	public EspectaculoDTO(){
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

	public long getIdTipoEspectaculo() {
		return idTipoEspectaculo;
	}

	public void setIdTipoEspectaculo(long idTipoEspectaculo) {
		this.idTipoEspectaculo = idTipoEspectaculo;
	}
	
}
