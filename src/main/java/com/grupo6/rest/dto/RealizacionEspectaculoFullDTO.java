package com.grupo6.rest.dto;

import java.util.Date;
import java.util.List;

import com.grupo6.persistence.model.RealizacionEspectaculo;

public class RealizacionEspectaculoFullDTO {

	private long id;

	private Date fecha;
	
	private SalaDTO sala;

	private long idEspectaculo;
	
	public RealizacionEspectaculoFullDTO(){
		
	}
	public RealizacionEspectaculoFullDTO(RealizacionEspectaculo x) {
		this.id = x.getId();
		this.fecha = x.getFecha();
		this.sala = new SalaDTO(x.getSala());
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

	public SalaDTO getSala() {
		return sala;
	}
	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}
	public long getIdEspectaculo() {
		return idEspectaculo;
	}

	public void setIdEspectaculo(long idEspectaculo) {
		this.idEspectaculo = idEspectaculo;
	}
	
	
}
