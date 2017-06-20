package com.grupo6.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupo6.persistence.model.Espectaculo;

public class EspectaculoFullDTO {

	private long id;

	private String nombre;
	
	private String descripcion;
	
	private List<TipoEspectaculoDTO> tipoEspectaculo;

	private List<RealizacionEspectaculoFullDTO> realizacionEspectaculo;
	
	public EspectaculoFullDTO(Espectaculo e){
		this.id = e.getId();
		this.nombre = e.getNombre();
		this.descripcion = e.getDescripcion();
		this.tipoEspectaculo  = new ArrayList<TipoEspectaculoDTO>();
		e.getTipoEspectaculo().stream().forEach(te ->{
			TipoEspectaculoDTO teDTO = new TipoEspectaculoDTO(te);
			this.tipoEspectaculo.add(teDTO);
		});
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

	public List<TipoEspectaculoDTO> getTipoEspectaculo() {
		return tipoEspectaculo;
	}


	public void setTipoEspectaculo(List<TipoEspectaculoDTO> tipoEspectaculo) {
		this.tipoEspectaculo = tipoEspectaculo;
	}


	public List<RealizacionEspectaculoFullDTO> getRealizacionEspectaculo() {
		return realizacionEspectaculo;
	}


	public void setRealizacionEspectaculo(List<RealizacionEspectaculoFullDTO> realizacionEspectaculo) {
		this.realizacionEspectaculo = realizacionEspectaculo;
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
		EspectaculoFullDTO other = (EspectaculoFullDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
