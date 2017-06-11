package com.grupo6.rest.dto;

import com.grupo6.persistence.model.Sala;

public class SalaDTO {

	private long id;

	private String nombre;
	
	private String direccion;
	
	private int total_localidad;

	public SalaDTO(Sala sala) {
		this.id = sala.getId();
		this.nombre = sala.getNombre();
		this.direccion = sala.getDireccion();
		this.total_localidad = sala.getTotal_localidad();
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTotal_localidad() {
		return total_localidad;
	}

	public void setTotal_localidad(int total_localidad) {
		this.total_localidad = total_localidad;
	}

	
}
