package com.grupo6.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.grupo6.persistence.model.Espectaculo;
import com.grupo6.persistence.model.TipoEspectaculo;

public class EspectaculoDTO {

	private long id;

	private String nombre;

	private String descripcion;

	private List<TipoEspectaculoDTO> tipoEspectaculo;

	public EspectaculoDTO(Espectaculo e) {
		this.id = e.getId();
		this.nombre = e.getNombre();
		this.descripcion = e.getDescripcion();
		this.tipoEspectaculo = new ArrayList<TipoEspectaculoDTO>();
		if (e.getTipoEspectaculo() != null && !e.getTipoEspectaculo().isEmpty()) {
			for (TipoEspectaculo te : e.getTipoEspectaculo()) {
				TipoEspectaculoDTO teDTO = new TipoEspectaculoDTO(te);
				this.tipoEspectaculo.add(teDTO);
			}
		}
	}

	public EspectaculoDTO() {
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
}
