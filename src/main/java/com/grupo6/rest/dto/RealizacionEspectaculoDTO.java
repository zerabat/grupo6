package com.grupo6.rest.dto;

import java.util.Date;

import com.grupo6.persistence.model.RealizacionEspectaculo;

public class RealizacionEspectaculoDTO {

	private long id;

	private Date fecha;
	
	private long idSala;

	private long idEspectaculo;

	public RealizacionEspectaculoDTO(){
		
	}
	public RealizacionEspectaculoDTO(RealizacionEspectaculo x) {
		this.id = x.getId();
		this.fecha = x.getFecha();
		this.idSala = x.getSala().getId();
		this.idEspectaculo = x.getEspectaculo().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getIdSala() {
		return idSala;
	}

	public void setIdSala(long idSala) {
		this.idSala = idSala;
	}

	public long getIdEspectaculo() {
		return idEspectaculo;
	}

	public void setIdEspectaculo(long idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}
	
	
}
