package com.grupo6.rest.dto;

import com.grupo6.persistence.model.Espectaculo;

public class EspectaculoUsuarioDTO {

	private long id;

	private String nombre;
	
	private String descripcion;
	
	private Long idAsiento;
	
	private SalaDTO sala;
	
	private RealizacionEspectaculoFullDTO realizacionEspectaculo;
	
	public EspectaculoUsuarioDTO(Espectaculo e){
		this.id = e.getId();
		this.nombre = e.getNombre();
		this.descripcion = e.getDescripcion();
	}

	public RealizacionEspectaculoFullDTO getRealizacionEspectaculo() {
		return realizacionEspectaculo;
	}

	public void setRealizacionEspectaculo(RealizacionEspectaculoFullDTO realizacionEspectaculo) {
		this.realizacionEspectaculo = realizacionEspectaculo;
	}

	public EspectaculoUsuarioDTO(){
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

	public Long getIdAsiento() {
		return idAsiento;
	}

	public void setIdAsiento(Long idAsiento) {
		this.idAsiento = idAsiento;
	}

	public SalaDTO getSala() {
		return sala;
	}

	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EspectaculoUsuarioDTO other = (EspectaculoUsuarioDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
